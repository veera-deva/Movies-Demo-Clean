plugins {
    with(deps.Dependencies.Plugins){
        id(androidLibrary)
        id(kotlinAndroid)
        id(hiltAndroid)
        id(kotlinKapt)
    }
}
android {
    namespace = "com.demo.data"
    compileSdk = 33
}

dependencies {

    implementation(project(deps.ProjectModules.domain))

    /*Android HILT dependencies*/
    with(deps.Dependencies.Hilt) {
        implementation(hiltAndroid)
        kapt(kaptHiltAndroidCompiler)
    }
    /*Kotlin coroutines*/
    implementation(deps.Dependencies.Kotlin.kotlinxCoroutines)
    implementation(deps.Dependencies.Kotlin.coroutinesCore)

    //*Retrofit*//*
    with(deps.Dependencies.Network) {
        implementation(retrofit)
        implementation(retrofitMoshiConvertor)
        implementation(okhttpLoggingInterceptor)
    }
    /*Testing dependencies*/

    testImplementation(deps.TestDependencies.JUnit.junit)

    /*Mockito dependencies*/
    with(deps.TestDependencies.Mockito) {
        testImplementation(mockitoCore)
        testImplementation(mockitoInline)
    }

    /*Mockk dependency*/
    testImplementation(deps.TestDependencies.Mockk.mockk)

    with(deps.TestDependencies) {
        /*Turbine library to test kotlin flows */
        testImplementation(turbine)
        testImplementation(striktCore)

        /*Coroutines test dependencies*/
        testImplementation(kotlinxCoroutinesTest)
    }
    implementation(kotlin("reflect"))

}