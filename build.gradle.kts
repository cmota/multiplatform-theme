// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("dev.icerock.moko:resources-generator:0.21.2")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}