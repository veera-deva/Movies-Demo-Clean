plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.demo.common_ui"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    sourceSets {
        getByName("main") {
            res {
                srcDirs("src\\main\\res", "src\\main\\res")
            }
        }
    }
}

dependencies {
/*AndroidX dependencies*/
    implementation(deps.Dependencies.AndroidX.coreKtx)
    implementation(deps.Dependencies.AndroidX.appCompat)
    /*Material design dependencies*/
    implementation(deps.Dependencies.Google.material)

    //Glide for image rendering
    implementation(deps.Dependencies.glide)
    kapt(deps.Dependencies.glideCompiler)

    /*Android Core unit test dependencies*/
    testImplementation(deps.TestDependencies.JUnit.junit)
    androidTestImplementation(deps.TestDependencies.AndroidX.junit)
    androidTestImplementation(deps.TestDependencies.AndroidX.espressoCore)
}