/**
 * libs
 */
object Libs {

    const val APP_COMPAT = "androidx.appcompat:appcompat:1.2.0"
    const val CORE_KTX = "androidx.core:core-ktx:1.3.2"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.0.2"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:1.1.0"

    // LIFE CYCLE
    const val LIFECYCLE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Lifecycle}"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Lifecycle}"
    const val LIFECYCLE_COMMON = "androidx.lifecycle:lifecycle-common-java8:${Version.Lifecycle}"
    const val LIFECYCLE_REACTIVE_STREAM =
        "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Version.Lifecycle}"

    const val ARCH_TEST = "androidx.arch.core:core-testing:${Version.Arch}"

    ///
    const val RX_JAVA_3 = "io.reactivex.rxjava3:rxjava:3.0.6"
    const val RX_ANDROID_3 = "io.reactivex.rxjava3:rxandroid:3.0.0"

    ///
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.Retrofit}"
    const val RETROFIT_RXJAVA3 = "com.squareup.retrofit2:adapter-rxjava3:${Version.Retrofit}"
    const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Version.Retrofit}"
    const val GSON = "com.google.code.gson:gson:2.8.6"

    const val HILT = "com.google.dagger:hilt-android:${Version.Hilt}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.Hilt}"
    const val HILTX = "androidx.hilt:hilt-lifecycle-viewmodel:${Version.HiltX}"
    const val HILTX_COMPILER = "androidx.hilt:hilt-compiler:${Version.HiltX}"

}