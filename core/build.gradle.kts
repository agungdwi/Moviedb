plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {


    api(libs.material)
    api(libs.androidx.appcompat)
    api(libs.androidx.core.ktx)
    api(libs.androidx.constraintlayout)
    api(libs.androidx.swiperefreshlayout)
    api(libs.glide)
    api(libs.androidx.paging.runtime.ktx)
    api(libs.androidx.activity)
    api(libs.junit)
    api(libs.androidx.junit)
    api(libs.androidx.espresso.core)

    api ("com.squareup.leakcanary:leakcanary-android:2.12")

    //coroutines Flow
    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.android)

    //navigation
    api(libs.androidx.navigation.fragment.ktx)
    api(libs.androidx.navigation.ui.ktx)
    api(libs.androidx.navigation.dynamic.features.fragment)

    //lifecycle
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.livedata.ktx)

    //Timber
    api ("com.jakewharton.timber:timber:4.7.1")

    //room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //koin
    api (libs.insert.koin.koin.core)
    api (libs.insert.koin.koin.android)

    annotationProcessor(libs.compiler)


}