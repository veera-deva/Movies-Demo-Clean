plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.demo.feature.movies"
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

    /*AndroidX dependencies*/
    implementation(deps.Dependencies.AndroidX.coreKtx)
    implementation(deps.Dependencies.AndroidX.appCompat)
    implementation(deps.Dependencies.Google.material)
    /*Android Core unit test dependencies*/
    testImplementation(deps.TestDependencies.JUnit.junit)
    androidTestImplementation(deps.TestDependencies.AndroidX.junit)
    androidTestImplementation(deps.TestDependencies.AndroidX.espressoCore)

}