pluginManagement {
    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention") version("0.4.0")
    }

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "MultiplatformTheme"

include(":androidApp")
include(":iosApp")

include(":shared-theme")
include(":shared-ui")
