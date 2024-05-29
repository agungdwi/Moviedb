plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("androidx.navigation.safeargs")
    id ("com.google.gms.google-services")
    id ("com.google.firebase.crashlytics")
}

android {
    namespace = "com.example.moviedb"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.moviedb"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }


    buildTypes {
        getByName("release") {
            // Enables code shrinking, obfuscation, and optimization for only
            // your project's release build type. Make sure to use a build
            // variant with `isDebuggable=false`.
            isMinifyEnabled = true

            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isShrinkResources = true

            proguardFiles(
                // Includes the default ProGuard rules files that are packaged with
                // the Android Gradle plugin. To learn more, go to the section about
                // R8 configuration files.
                getDefaultProguardFile("proguard-android-optimize.txt"),

                // Includes a local, custom Proguard rules file
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            // Enables code shrinking, obfuscation, and optimization for only
            // your project's release build type. Make sure to use a build
            // variant with `isDebuggable=false`.
            isMinifyEnabled = false

            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isShrinkResources = false

            proguardFiles(
                // Includes the default ProGuard rules files that are packaged with
                // the Android Gradle plugin. To learn more, go to the section about
                // R8 configuration files.
                getDefaultProguardFile("proguard-android-optimize.txt"),

                // Includes a local, custom Proguard rules file
                "proguard-rules.pro"
            )
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"

    }

    buildFeatures {
        viewBinding = true
    }
    dynamicFeatures += setOf(":favorites")
}

dependencies {
    implementation(project(":core"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    applySharedDeps()

    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation ("androidx.activity:activity:1.9.0")
    implementation (libs.androidx.core.splashscreen)

//crashnalytics
    implementation ("com.google.firebase:firebase-analytics-ktx:22.0.0")
    implementation ("com.google.firebase:firebase-crashlytics:19.0.0")
}