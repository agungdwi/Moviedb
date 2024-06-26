import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.applySharedDeps() {
    add("implementation", "androidx.core:core-ktx:1.13.1")
    add("implementation", "androidx.appcompat:appcompat:1.6.1")
    add("implementation", "com.google.android.material:material:1.12.0")
    add("implementation", "androidx.constraintlayout:constraintlayout:2.1.4")
    add("implementation", "androidx.paging:paging-runtime-ktx:3.2.1")
    add("implementation", "com.github.bumptech.glide:glide:4.13.2")
    add("testImplementation", "junit:junit:4.13.2")
    add("androidTestImplementation", "androidx.test.ext:junit:1.1.5")
    add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.5.1")
    add("debugImplementation", "com.squareup.leakcanary:leakcanary-android:2.12")

    //navigation
    add("implementation", "androidx.navigation:navigation-fragment-ktx:2.7.0")
    add("implementation", "androidx.navigation:navigation-ui-ktx:2.7.0")
    add("implementation", "androidx.navigation:navigation-dynamic-features-fragment:2.7.0")

    //lifecycle
    add("implementation", "androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    add("implementation", "androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    //koin
    add("implementation", "io.insert-koin:koin-android:3.2.0")
    add("implementation", "io.insert-koin:koin-core:3.2.0")

    add("implementation", "androidx.multidex:multidex:2.0.1")

    //shimmer
    add("implementation", "com.facebook.shimmer:shimmer:0.5.0")

    //lottie
    add("implementation", "com.airbnb.android:lottie:6.4.1")

    //unit test
    add("androidTestImplementation", "androidx.arch.core:core-testing:2.2.0")
    add("androidTestImplementation", "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    add("testImplementation", "androidx.arch.core:core-testing:2.2.0")
    add("testImplementation", "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    add("testImplementation", "org.mockito:mockito-core:4.4.0")
    add("testImplementation", "org.mockito:mockito-inline:4.4.0")

}

