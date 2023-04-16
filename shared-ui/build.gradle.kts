@file:Suppress("UnstableApiUsage")

import org.jetbrains.compose.desktop.application.tasks.AbstractNativeMacApplicationPackageAppDirTask
import org.jetbrains.kotlin.gradle.plugin.mpp.AbstractExecutable
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBinary
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import org.jetbrains.kotlin.library.impl.KotlinLibraryLayoutImpl
import java.io.File
import java.io.FileFilter
import org.jetbrains.kotlin.konan.file.File as KonanFile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version("1.4.0")
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform-resources")
}

version = "1.0.0"

multiplatformResources {
    multiplatformResourcesPackage = "com.cmota.sample"
    iosBaseLocalizationRegion = "en"
}

kotlin {
    android()

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
                api(compose.foundation)
                api(compose.runtime)
                api(compose.material)
                api(compose.materialIconsExtended)
                api(compose.ui)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.components.resources)

                api(project(":shared-theme"))

                api("moe.tlaster:precompose:1.3.15")
                api("dev.icerock.moko:resources:0.21.2")
                api("dev.icerock.moko:resources-compose:0.21.2")
                api("io.github.qdsfdhvh:image-loader:1.4.0")
            }
        }
        val androidMain by getting
        val uikitX64Main by getting
        val uikitArm64Main by getting
        val uikitMain by creating {
            dependsOn(commonMain)

            uikitX64Main.dependsOn(this)
            uikitArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/resources")
    defaultConfig {
        minSdk = 23
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "com.cmota.sample"
}
dependencies {
    implementation("androidx.navigation:navigation-runtime-ktx:2.5.3")
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
