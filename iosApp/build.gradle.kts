@file:Suppress("UnstableApiUsage")

import org.jetbrains.compose.experimental.dsl.IOSDevices
import org.jetbrains.compose.experimental.uikit.tasks.ExperimentalPackComposeApplicationForXCodeTask
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import java.io.File
import kotlin.reflect.full.declaredMemberProperties

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.4.0"
}

version = "1.0.0"

kotlin {

    listOf(
        iosX64("uikitX64"),
        iosArm64("uikitArm64"),
    ).forEach {
        it.binaries {
            executable {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
                // TODO: the current compose binary surprises LLVM, so disable checks for now.
                freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)

                implementation(project(":shared-theme"))
                implementation(project(":shared-ui"))

                implementation("moe.tlaster:precompose:1.3.15")
            }
        }
        val uikitX64Main by getting
        val uikitArm64Main by getting
        val uikitMain by creating {
            dependsOn(commonMain)

            uikitX64Main.dependsOn(this)
            uikitArm64Main.dependsOn(this)
        }
    }
}

compose.experimental {
    uikit.application {
        bundleIdPrefix = "com.cmota.sample"
        projectName = "Sample"
        deployConfigurations {
            simulator("IPhone8") {
                //Usage: ./gradlew iosDeployIPhone8Debug
                device = IOSDevices.IPHONE_8
            }
            simulator("IPhone13Mini") {
                device = IOSDevices.IPHONE_13_MINI
            }
            simulator("IPad") {
                //Usage: ./gradlew iosDeployIPadDebug
                device = IOSDevices.IPAD_MINI_6th_Gen
            }
            connectedDevice("Device") {
                //Usage: ./gradlew iosDeployDeviceRelease
                teamId="***"
            }
        }
    }
}

kotlin {
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.all {
            // TODO: the current compose binary surprises LLVM, so disable checks for now.
            freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
        }
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}

// todo: Remove when resolved: https://github.com/icerockdev/moko-resources/issues/395
// copy .bundle from all .klib to .kexe
tasks.withType<KotlinNativeLink>()
    .configureEach {
        val linkTask: KotlinNativeLink = this
        val outputDir: File = this.outputFile.get().parentFile

        @Suppress("ObjectLiteralToLambda") // lambda broke up-to-date
        val action = object : Action<Task> {
            override fun execute(t: Task) {
                (linkTask.libraries + linkTask.sources)
                    .filter { library -> library.extension == "klib" }
                    .filter(File::exists)
                    .forEach { inputFile ->
                        val klibKonan = org.jetbrains.kotlin.konan.file.File(inputFile.path)
                        val klib = org.jetbrains.kotlin.library.impl.KotlinLibraryLayoutImpl(
                            klib = klibKonan,
                            component = "default"
                        )
                        val layout = klib.extractingToTemp

                        // extracting bundles
                        layout
                            .resourcesDir
                            .absolutePath
                            .let(::File)
                            .listFiles { file: File -> file.extension == "bundle" }
                            // copying bundles to app
                            ?.forEach {
                                logger.info("${it.absolutePath} copying to $outputDir")
                                it.copyRecursively(
                                    target = File(outputDir, it.name),
                                    overwrite = true
                                )
                            }
                    }
            }
        }
        doLast(action)
    }

// copy .bundle from .kexe to .app
tasks.withType<ExperimentalPackComposeApplicationForXCodeTask>()
    .configureEach {
        val packTask: ExperimentalPackComposeApplicationForXCodeTask = this

        val kclass = ExperimentalPackComposeApplicationForXCodeTask::class
        val kotlinBinaryField =
            kclass.declaredMemberProperties.single { it.name == "kotlinBinary" }
        val destinationDirField =
            kclass.declaredMemberProperties.single { it.name == "destinationDir" }
        val executablePathField =
            kclass.declaredMemberProperties.single { it.name == "executablePath" }

        @Suppress("ObjectLiteralToLambda") // lambda broke up-to-date
        val action = object : Action<Task> {
            override fun execute(t: Task) {
                val kotlinBinary: RegularFile =
                    (kotlinBinaryField.get(packTask) as RegularFileProperty).get()
                val destinationDir: Directory =
                    (destinationDirField.get(packTask) as DirectoryProperty).get()
                val executablePath: String =
                    (executablePathField.get(packTask) as Provider<String>).get()

                val outputDir: File = File(destinationDir.asFile, executablePath).parentFile

                val bundleSearchDir: File = kotlinBinary.asFile.parentFile
                bundleSearchDir
                    .listFiles { file: File -> file.extension == "bundle" }
                    ?.forEach { file ->
                        file.copyRecursively(File(outputDir, file.name), true)
                    }
            }
        }
        doLast(action)
    }
