plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.demo.domain"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
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

    /*DI Hilt*/
    api(deps.Dependencies.Hilt.hiltAndroid)
    kapt(deps.Dependencies.Hilt.kaptHiltAndroidCompiler)

    /*Testing dependencies*/

    testImplementation(deps.TestDependencies.JUnit.junit)
    androidTestImplementation(deps.TestDependencies.AndroidX.junit)

    /*Coroutines test dependencies*/
    testImplementation(deps.TestDependencies.kotlinxCoroutinesTest)

    /*Mockito dependencies*/
    with(deps.TestDependencies.Mockito) {
        testImplementation(mockitoCore)
        testImplementation(mockitoInline)
        androidTestImplementation(mockitoAndroid)

    }

    /*Turbine library to test kotlin flows */
    testImplementation(deps.TestDependencies.turbine)
}