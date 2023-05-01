package deps

import deps.Versions.activityKtxVersion
import deps.Versions.appCompatVersion
import deps.Versions.constraintLayoutVersion
import deps.Versions.coreKTXVersion
import deps.Versions.hiltAndroidVersion
import deps.Versions.hiltKaptCompilerVersion
import deps.Versions.hiltNavigationFragmentVersion
import deps.Versions.kaptHiltCompilerVersion
import deps.Versions.kotlinxCoroutinesVersion
import deps.Versions.lifecycleExtension
import deps.Versions.lifecycleLiveDataKtx
import deps.Versions.lifecycleViewModelKtx
import deps.Versions.materialVersion
import deps.Versions.mockk
import deps.Versions.navigationFragmentKtx
import deps.Versions.navigationUIKtx
import deps.Versions.okHttpLoggingInterceptorVersion
import deps.Versions.okHttpVersion
import deps.Versions.retrofitGsonConvertorVersion
import deps.Versions.retrofitVersion
import deps.Versions.testCore
import deps.Versions.testJunit
import deps.Versions.testKotlinCoroutines

object Dependencies {

    object AndroidX {
        val coreKtx by lazy { "androidx.core:core-ktx:${coreKTXVersion}" }
        val appCompat by lazy { "androidx.appcompat:appcompat:${appCompatVersion}" }
        val material by lazy { "com.google.android.material:material:${materialVersion}" }
        val constrainLayout by lazy {
            "androidx.constraintlayout:constraintlayout:${constraintLayoutVersion}"
        }
        val navigationFragment by lazy {
            "androidx.navigation:navigation-fragment-ktx:${navigationFragmentKtx}"
        }
        val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${navigationUIKtx}" }
        val activityKTX by lazy { "androidx.activity:activity-ktx:${activityKtxVersion}" }
        val lifecycleExt by lazy { "androidx.lifecycle:lifecycle-extensions:${lifecycleExtension}" }
        val lifecycleLiveData by lazy {
            "androidx.lifecycle:lifecycle-livedata-ktx:${lifecycleLiveDataKtx}"
        }
        val lifecycleViewModel by lazy {
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycleViewModelKtx}"
        }

    }


    object Google {
        val material by lazy { "com.google.android.material:material:$materialVersion" }
    }

    object Kotlin {
        val kotlinxCoroutines by lazy {
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${kotlinxCoroutinesVersion}"
        }
        val testKotlinx by lazy {
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${testKotlinCoroutines}"
        }
        val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.coroutinesCoreJVM}" }
    }

    object Hilt {
        val hiltAndroid by lazy { "com.google.dagger:hilt-android:${hiltAndroidVersion}" }
        val kaptHiltAndroidCompiler by lazy {
            "com.google.dagger:hilt-android-compiler:${hiltKaptCompilerVersion}"
        }
        val kaptHiltCompiler by lazy { "androidx.hilt:hilt-compiler:${kaptHiltCompilerVersion}" }
        val hiltNavigationFragment by lazy {
            "androidx.hilt:hilt-navigation-fragment:${hiltNavigationFragmentVersion}"
        }
    }

    object Network {
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${retrofitVersion}" }
        val retrofitGsonConvertor by lazy {
            "com.squareup.retrofit2:converter-gson:${retrofitGsonConvertorVersion}"
        }
        val retrofitMoshiConvertor by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.retrofitMoshi}" }
        val moshi by lazy { "com.squareup.moshi:moshi:${Versions.Moshi}" }
        val moshiCodeGen by lazy { "com.squareup.moshi:moshi-kotlin-codegen:${Versions.Moshi}" }

        val okhttp by lazy { "com.squareup.okhttp3:okhttp:${okHttpVersion}" }
        val okhttpLoggingInterceptor by lazy {
            "com.squareup.okhttp3:logging-interceptor:${okHttpLoggingInterceptorVersion}"
        }
    }

    object Testing {
        val unitTestingCore by lazy { "androidx.test:core-ktx:${testCore}" }
        val unitTestingJunit by lazy { "junit:junit:${testJunit}" }
        val unitTestingMockk by lazy { "io.mockk:mockk:${mockk}" }

    }
    //Glide for image rendering
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glideVersion}" }
    val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glideVersion}" }

}