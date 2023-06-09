@file:Suppress("UnstableApiUsage")

plugins {
    id(deps.Dependencies.Plugins.androidLibrary)
    id(deps.Dependencies.Plugins.kotlinAndroid)
    id(deps.Dependencies.Plugins.kotlinKapt)
}

android {
    namespace = "com.demo.shared_test"
    compileSdk = deps.AndroidSettings.compileSdk

    defaultConfig {
        minSdk = deps.AndroidSettings.minSdk
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
}

dependencies {

    implementation(project(deps.ProjectModules.app))
    implementation(project(deps.ProjectModules.featureMovies))

    /*Coroutines*/
    implementation(deps.Dependencies.Kotlin.coroutinesCore)
    /*Android Core unit test dependencies*/
    implementation(deps.TestDependencies.JUnit.junit)
    implementation(deps.TestDependencies.AndroidX.junit)
    implementation(deps.TestDependencies.AndroidX.espressoCore)

    /*Hilt Testing dependencies*/
    with(deps.TestDependencies.Hilt) {
        implementation(hiltAndroidTesting)
        kaptAndroidTest(hiltAndroidCompiler)
        implementation(hiltAndroidCompiler)
    }
    /*Coroutines test dependencies*/
    implementation(deps.TestDependencies.kotlinxCoroutinesTest)
    /*Flow unit testing dependencies*/
    implementation(deps.TestDependencies.turbine)

}