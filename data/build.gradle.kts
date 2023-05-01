plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.demo.data"
    compileSdk = deps.AndroidSettings.compileSdk

    defaultConfig {
        minSdk = deps.AndroidSettings.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
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
}

dependencies {

    implementation(project(deps.ProjectModules.domain))

    /*Android HILT dependencies*/
    implementation(deps.Dependencies.Hilt.hiltAndroid)
    kapt(deps.Dependencies.Hilt.kaptHiltAndroidCompiler)

    /*Kotlin coroutines*/
    implementation(deps.Dependencies.Kotlin.kotlinxCoroutines)
    implementation(deps.Dependencies.Kotlin.coroutinesCore)

    //*Retrofit*//*
    implementation(deps.Dependencies.Network.retrofit)
    implementation(deps.Dependencies.Network.retrofitMoshiConvertor)
    implementation(deps.Dependencies.Network.okhttpLoggingInterceptor)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}