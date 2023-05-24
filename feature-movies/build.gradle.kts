@file:Suppress("UnstableApiUsage")

plugins {
    with(deps.Dependencies.Plugins) {
        id(androidLibrary)
        id(kotlinAndroid)
        id(hiltAndroid)
        id(kotlinKapt)
        id(kotlinParcelize)
        id(navigationSafArgs)
    }
}

android {
    namespace = "com.demo.feature.movies"
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

    with(deps.ProjectModules) {
        implementation(project(commonUi))
        implementation(project(data))
        implementation(project(domain))
    }

    /*AndroidX dependencies*/
    with(deps.Dependencies.AndroidX) {
        implementation(coreKtx)
        implementation(appCompat)
        /*Navigation dependencies*/
        api(navigationFragment)
        api(navigationUi)
        api(navigationFeaturesFragment)
    }

    implementation(deps.Dependencies.Google.material)

    /*Android HILT dependencies*/
    with(deps.Dependencies.Hilt) {
        implementation(hiltAndroid)
        kapt(kaptHiltAndroidCompiler)

    }
    /*Android Core unit test dependencies*/
    with(deps.TestDependencies.AndroidX) {
        testImplementation(deps.TestDependencies.JUnit.junit)
        androidTestImplementation(junit)
        androidTestImplementation(espressoCore)
        // Testing Fragments in Isolation
        debugImplementation(fragmentTesting)
    }
    /*Hilt Testing dependencies*/
    with(deps.TestDependencies.Hilt) {
        androidTestImplementation(hiltAndroidTesting)
        kaptAndroidTest(hiltAndroidCompiler)
        androidTestAnnotationProcessor(hiltAndroidCompiler)
    }
    /*Mockk dependency*/
    testImplementation(deps.TestDependencies.Mockk.mockk)
    with(deps.TestDependencies) {
        /*Strikt assertion library*/
        testImplementation(striktCore)
        /*Coroutines test dependencies*/
        testImplementation(kotlinxCoroutinesTest)
        /*Flow unit testing dependencies*/
        testImplementation(turbine)
    }
    /*Shared test module*/
    testImplementation(project(deps.ProjectModules.sharedTest))
}
