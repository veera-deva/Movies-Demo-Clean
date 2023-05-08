import deps.ProjectModules

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.demo.shared_test"
    compileSdk = 33

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
}

dependencies {

    implementation(project(ProjectModules.app))
    implementation(project(ProjectModules.featureMovies))

    /*Coroutines*/
    implementation(deps.Dependencies.Kotlin.coroutinesCore)
    /*Android Core unit test dependencies*/
    implementation(deps.TestDependencies.JUnit.junit)
    implementation(deps.TestDependencies.AndroidX.junit)
    implementation(deps.TestDependencies.AndroidX.espressoCore)

    /*Hilt Testing dependencies*/
    implementation(deps.TestDependencies.Hilt.androidTesting)
    kaptAndroidTest(deps.TestDependencies.Hilt.androidCompiler)
    implementation(deps.TestDependencies.Hilt.androidCompiler)

    /*Coroutines test dependencies*/
    implementation(deps.TestDependencies.kotlinxCoroutinesTest)
    implementation(deps.TestDependencies.mockito.mockitoCore)
    implementation(deps.TestDependencies.mockito.mockitoInline)
    implementation(deps.TestDependencies.mockito.mockitoAndroid)
    implementation(deps.TestDependencies.turbine)

}