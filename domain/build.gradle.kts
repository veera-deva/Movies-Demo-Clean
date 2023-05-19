plugins {
    id(deps.Dependencies.Plugins.kotlin)
    id(deps.Dependencies.Plugins.javaLibrary)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    // JavaX
    implementation(deps.Dependencies.javax)

    /*Coroutines*/
    implementation(deps.Dependencies.Kotlin.coroutinesCore)

    /*Testing dependencies*/
    testImplementation(deps.TestDependencies.JUnit.junit)

    /*Coroutines test dependencies*/
    testImplementation(deps.TestDependencies.kotlinxCoroutinesTest)

    /*Mockito dependencies*/
    with(deps.TestDependencies.Mockito) {
        testImplementation(mockitoCore)
        testImplementation(mockitoInline)
    }

    /*Turbine library to test kotlin flows */
    testImplementation(deps.TestDependencies.turbine)
}