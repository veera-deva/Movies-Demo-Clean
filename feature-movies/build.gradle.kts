plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
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
    sourceSets {
        getByName("main") {
            java {
                srcDirs("src\\main\\java", "src\\main\\java")
            }
            res {
                srcDirs("src\\main\\res", "src\\main\\res")
            }
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(deps.ProjectModules.commonUi))
    implementation(project(deps.ProjectModules.data))
    implementation(project(deps.ProjectModules.domain))


    /*AndroidX dependencies*/
    implementation(deps.Dependencies.AndroidX.coreKtx)
    implementation(deps.Dependencies.AndroidX.appCompat)
    implementation(deps.Dependencies.Google.material)

    /*Android HILT dependencies*/
    implementation(deps.Dependencies.Hilt.hiltAndroid)
    kapt(deps.Dependencies.Hilt.kaptHiltAndroidCompiler)

    api(deps.Dependencies.AndroidX.navigationFragment)
    api(deps.Dependencies.AndroidX.navigationUi)
    api(deps.Dependencies.AndroidX.navigationFeaturesFragment)

    /*Android Core unit test dependencies*/
    testImplementation(deps.TestDependencies.JUnit.junit)
    androidTestImplementation(deps.TestDependencies.AndroidX.junit)
    androidTestImplementation(deps.TestDependencies.AndroidX.espressoCore)

    /*Hilt Testing dependencies*/
    androidTestImplementation(deps.TestDependencies.Hilt.androidTesting)
    kaptAndroidTest(deps.TestDependencies.Hilt.androidCompiler)
    androidTestAnnotationProcessor(deps.TestDependencies.Hilt.androidCompiler)

    /*Coroutines test dependencies*/
    testImplementation(deps.TestDependencies.kotlinxCoroutinesTest)
    testImplementation(deps.TestDependencies.Mockito.mockitoCore)
    testImplementation(deps.TestDependencies.Mockito.mockitoInline)
    androidTestImplementation(deps.TestDependencies.Mockito.mockitoAndroid)
    testImplementation(deps.TestDependencies.turbine)
    testImplementation(project(deps.ProjectModules.sharedTest))

}