

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false
    alias(libs.plugins.android.dynamic.feature) apply false
    id ("com.google.gms.google-services") version "4.3.15" apply false
    id ("com.google.firebase.crashlytics") version "2.9.4" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath (libs.androidx.navigation.safe.args.gradle.plugin)
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}