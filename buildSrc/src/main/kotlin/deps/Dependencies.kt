package deps

object Dependencies {

    object Plugins {
        const val kotlin = "kotlin"
        const val javaLibrary = "java-library"
        const val android = "com.android.application"
        const val kotlinAndroid = "kotlin-android"
        const val navigationSafArgs = "androidx.navigation.safeargs.kotlin"
        const val kotlinKapt = "kotlin-kapt"
        const val hiltAndroid = "com.google.dagger.hilt.android"
        const val androidLibrary = "com.android.library"
        const val kotlinParcelize = "kotlin-parcelize"
        const val jetbrainsKotlinAndroid = "org.jetbrains.kotlin.android"

    }

    object AndroidX {
        val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKTXVersion}" }
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompatVersion}" }
        val material by lazy { "com.google.android.material:material:${Versions.materialVersion}" }
        val constrainLayout by lazy {
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        }
        val navigationFragment by lazy {
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
        }
        val navigationFeaturesFragment by lazy {
            "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigationVersion}"
        }
        val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}" }
    }


    object Google {
        val material by lazy { "com.google.android.material:material:${Versions.materialVersion}" }
    }

    object Kotlin {
        val kotlinxCoroutines by lazy {
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutinesVersion}"
        }
        val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.coroutinesCoreJVM}" }
    }

    object Hilt {
        val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hiltAndroidVersion}" }
        val kaptHiltAndroidCompiler by lazy {
            "com.google.dagger:hilt-android-compiler:${Versions.hiltKaptCompilerVersion}"
        }
    }

    object Network {
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}" }
        val retrofitMoshiConvertor by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.retrofitMoshi}" }

        val okhttpLoggingInterceptor by lazy {
            "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptorVersion}"
        }
    }

    val javax by lazy { "javax.inject:javax.inject:${Versions.javaxInjectVersion}" }

    //Glide for image rendering
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glideVersion}" }
    val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glideVersion}" }

}