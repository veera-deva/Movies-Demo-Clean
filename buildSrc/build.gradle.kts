import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(gradleApi())
}
repositories {
    mavenCentral()
}
gradlePlugin {
    plugins {
        create("GenerateGradlePlugin") {
            id = "movies.ci.plugin"
            implementationClass = "android.plugin.GenerateGradlePlugin"
        }

    }
}