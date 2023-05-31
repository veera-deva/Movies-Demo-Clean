plugins {
    with(deps.Dependencies.Plugins) {
        id(androidLibrary)
        id(kotlinAndroid)
        id(hiltAndroid)
        id(kotlinKapt)
    }
}
android {
    namespace = "com.demo.data"
    compileSdk = deps.AndroidSettings.compileSdk

    defaultConfig{
        minSdk = deps.AndroidSettings.minSdk
    }
}

dependencies {

    implementation(project(deps.ProjectModules.domain))

    /*Android HILT dependencies*/
    with(deps.Dependencies.Hilt) {
        implementation(hiltAndroid)
        kapt(kaptHiltAndroidCompiler)
    }
    /*Kotlin coroutines*/
    with(deps.Dependencies.Kotlin) {
        implementation(kotlinxCoroutines)
        implementation(coroutinesCore)
    }

    //*Retrofit*//*
    with(deps.Dependencies.Network) {
        implementation(retrofit)
        implementation(retrofitMoshiConvertor)
        implementation(okhttpLoggingInterceptor)
    }
    /*Testing dependencies*/

    testImplementation(deps.TestDependencies.JUnit.junit)
    /*Mockk dependency*/
    testImplementation(deps.TestDependencies.Mockk.mockk)

    with(deps.TestDependencies) {
        /*Turbine library to test kotlin flows */
        testImplementation(turbine)
        testImplementation(striktCore)
        /*Coroutines test dependencies*/
        testImplementation(kotlinxCoroutinesTest)
    }
}