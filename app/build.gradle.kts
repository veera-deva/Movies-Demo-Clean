@file:Suppress("UnstableApiUsage")

import deps.ProjectModules
import deps.TestDependencies

plugins {
    id(deps.Dependencies.Plugins.android)
    id(deps.Dependencies.Plugins.kotlinAndroid)
    id(deps.Dependencies.Plugins.hiltAndroid)
    id(deps.Dependencies.Plugins.kotlinKapt)
    id(deps.Dependencies.Plugins.navigationSafArgs)
}

android {
    namespace = deps.Configs.nameSpace
    compileSdk = deps.AndroidSettings.compileSdk

    defaultConfig {
        applicationId = deps.Configs.applicationId
        minSdk = deps.AndroidSettings.minSdk
        targetSdk = deps.AndroidSettings.targetSdk
        versionCode = deps.Configs.versionCode
        versionName = deps.Configs.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            testCoverage { enableUnitTestCoverage }
        }
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(ProjectModules.domain))
    implementation(project(ProjectModules.commonUi))
    implementation(project(ProjectModules.featureMovies))

    /*AndroidX dependencies*/
    with(deps.Dependencies.AndroidX) {
        implementation(coreKtx)
        implementation(appCompat)
        implementation(constrainLayout)
        api(navigationFragment)
        api(navigationUi)
        api(navigationFeaturesFragment)
    }

    /*Coroutines*/
    implementation(deps.Dependencies.Kotlin.coroutinesCore)
    /*Material design dependencies*/
    implementation(deps.Dependencies.Google.material)

    /*Android HILT dependencies*/
    with(deps.Dependencies.Hilt) {
        implementation(hiltAndroid)
        kapt(kaptHiltAndroidCompiler)

    }
    /*Hilt Testing dependencies*/
    with(TestDependencies.Hilt) {
        androidTestImplementation(hiltAndroidTesting)
        kaptAndroidTest(hiltAndroidCompiler)
        androidTestAnnotationProcessor(hiltAndroidCompiler)
    }

    /*Android Core unit test dependencies*/
    testImplementation(TestDependencies.JUnit.junit)
    androidTestImplementation(TestDependencies.AndroidX.junit)
    androidTestImplementation(TestDependencies.AndroidX.espressoCore)

    /*Mockito test dependencies*/
    with(TestDependencies.Mockito) {
        testImplementation(mockitoCore)
        testImplementation(mockitoInline)
        androidTestImplementation(mockitoAndroid)
    }

    /*Coroutines test dependencies*/
    testImplementation(TestDependencies.kotlinxCoroutinesTest)

    /*Turbine library to test kotlin flows */
    testImplementation(TestDependencies.turbine)

    testImplementation(project(ProjectModules.sharedTest))
}

