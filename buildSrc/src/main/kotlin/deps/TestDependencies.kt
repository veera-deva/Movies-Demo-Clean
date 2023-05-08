package deps

object TestDependencies {

    object AndroidX {
        val core by lazy {
            "androidx.test:core:${Versions.androidxTest}"
        }
        val coreKtx by lazy {
            "androidx.test:core-ktx:${Versions.androidxTest}"
        }
        val runner by lazy {
            "androidx.test:runner:${Versions.androidxTest}"
        }
        val rules by lazy {
            "androidx.test:rules:${Versions.androidxTest}"
        }
        val espressoCore by lazy {
            "androidx.test.espresso:espresso-core:${Versions.espresso}"
        }
        val espressoContrib by lazy {
            "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
        }
        val junit by lazy {
            "androidx.test.ext:junit:${Versions.androidxJunit}"
        }
        val coreTesting by lazy {
            "androidx.arch.core:core-testing:${Versions.coreTesting}"
        }
        val navigationTesting by lazy {
            "androidx.navigation:navigation-testing:${Versions.navigationVersion}"
        }

    }

    val turbine by lazy { "app.cash.turbine:turbine:${Versions.turbine}" }

    object Mockito {
        val mockitoCore by lazy {
            "org.mockito:mockito-core:${Versions.mockito}"
        }
        val mockitoAndroid by lazy {
            "org.mockito:mockito-android:${Versions.mockitoAndroid}"
        }
        val mockitoInline by lazy {
            "org.mockito:mockito-inline:${Versions.mockitoInline}"
        }
    }

    val kotlinxCoroutinesTest by lazy {
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.testKotlinCoroutines}"
    }

    object JUnit {
        val junit by lazy {
            "junit:junit:${Versions.junit}"
        }
        val junitPlatformRunner by lazy {
            "org.junit.platform:junit-platform-runner:${Versions.junitPlatformRunner}"
        }
    }

    object Mockk {
        val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
        val mockkAgentJvm by lazy { "io.mockk:mockk-agent-jvm:${Versions.mockk}" }
    }

    object Hilt {
        val hiltAndroidTesting by lazy {
            "com.google.dagger:hilt-android-testing:${Versions.hiltTest}"
        }
        val hiltAndroidCompiler by lazy {
            "com.google.dagger:hilt-android-compiler:${Versions.hiltTest}"
        }
    }

    val mockWebServer by lazy { "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}" }
}