import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.applySharedDeps() {
    add("implementation", "androidx.core:core-ktx:1.13.1")
    add("implementation", "androidx.appcompat:appcompat:1.6.1")
    add("implementation", "com.google.android.material:material:1.12.0")
    add("implementation", "androidx.constraintlayout:constraintlayout:2.1.4")
//    add("implementation", "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    add("implementation", "androidx.paging:paging-runtime-ktx:3.2.1")
//    add("implementation", "androidx.activity:activity:1.9.0")
    add("implementation", "com.github.bumptech.glide:glide:4.13.2")
    add("testImplementation", "junit:junit:4.13.2")
    add("androidTestImplementation", "androidx.test.ext:junit:1.1.5")
    add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.5.1")
    add("debugImplementation", "com.squareup.leakcanary:leakcanary-android:2.12")

    //navigation
    add("implementation", "androidx.navigation:navigation-fragment-ktx:2.7.0")
    add("implementation", "androidx.navigation:navigation-ui-ktx:2.7.0")
    add("implementation", "androidx.navigation:navigation-dynamic-features-fragment:2.7.0")

    //lifcycle
    add("implementation", "androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    add("implementation", "androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    //Timber
    add("implementation", "com.jakewharton.timber:timber:4.7.1")

    //koin
    add("implementation", "io.insert-koin:koin-android:3.2.0")
    add("implementation", "io.insert-koin:koin-core:3.2.0")

    add("implementation", "androidx.multidex:multidex:2.0.1")


}

