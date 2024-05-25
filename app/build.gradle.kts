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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
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
    implementation (libs.androidx.core.splashscreen)

//crashnalytics
    implementation ("com.google.firebase:firebase-analytics-ktx:22.0.0")
    implementation ("com.google.firebase:firebase-crashlytics:19.0.0")
}