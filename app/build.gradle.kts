import deps.Dependencies
import deps.ProjectModules
import deps.TestDependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
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
    implementation(project(ProjectModules.featureModule))

    implementation(project(ProjectModules.data))

    /*AndroidX dependencies*/
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.constrainLayout)
    implementation(Dependencies.AndroidX.navigationFragment)
    implementation(Dependencies.AndroidX.navigationUi)

    /*Coroutines*/
    implementation(Dependencies.Kotlin.coroutinesCore)
    /*Material design dependencies*/
    implementation(Dependencies.Google.material)

    /*Android HILT dependencies*/
    implementation(Dependencies.Hilt.hiltAndroid)
    kapt(Dependencies.Hilt.kaptHiltAndroidCompiler)

    //Glide for image rendering
    implementation(Dependencies.glide)
    kapt(Dependencies.glideCompiler)

    /*Android Core unit test dependencies*/
    testImplementation(TestDependencies.JUnit.junit)
    androidTestImplementation(TestDependencies.AndroidX.junit)
    androidTestImplementation(TestDependencies.AndroidX.espressoCore)

    /*Hilt Testing dependencies*/
    androidTestImplementation(TestDependencies.Hilt.androidTesting)
    kaptAndroidTest(TestDependencies.Hilt.androidCompiler)
    androidTestAnnotationProcessor(TestDependencies.Hilt.androidCompiler)

    /*Coroutines test dependencies*/
    testImplementation(TestDependencies.kotlinxCoroutinesTest)
    testImplementation(TestDependencies.mockito.mockitoCore)
    testImplementation(TestDependencies.mockito.mockitoInline)
    androidTestImplementation(TestDependencies.mockito.mockitoAndroid)
    testImplementation(TestDependencies.turbine)


}