plugins {
    id(deps.Dependencies.Plugins.kotlin)
    id(deps.Dependencies.Plugins.javaLibrary)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    /*Coroutines*/
    implementation(deps.Dependencies.Kotlin.coroutinesCore)
    // JavaX
    implementation(deps.Dependencies.javax)

    with(deps.TestDependencies) {
        /*Coroutines test dependencies*/
        testImplementation(kotlinxCoroutinesTest)
        /*Strikt assertion library*/
        testImplementation(striktCore)
    }
    /*Testing dependencies*/
    testImplementation(deps.TestDependencies.JUnit.junit)
    /*Mockk dependency*/
    testImplementation(deps.TestDependencies.Mockk.mockk)
}