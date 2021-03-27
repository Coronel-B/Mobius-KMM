import org.gradle.api.JavaVersion

/**
 * Abbreviation: https://www.abbreviations.com/abbreviation/Version
 * PRECONDITION: Only use in Kotlin DSL (e.g: build.gradle.kts), not Groovy
 */
object V {
    const val minSdk = 24
    const val targetSdk = 30
    const val compileSdk = 30
    const val buildToolsVersion = "31.0.0 rc1"


    const val kotlin = "1.4.30"
    const val androidGradlePlugin = "4.2.0-beta06"

    const val cocoapodsext = "0.11"
    const val coroutines = "1.4.1"
    const val kermit = "0.1.8"
    const val karmok = "0.1.8"
    const val ktor = "1.5.0"
    const val ktorMockNative = "1.3.1"
    const val junit = "4.13"
    const val material = "1.3.0"
    const val multiplatformSettings = "0.6.3"
    const val robolectric = "4.4"
    const val sqlDelight = "1.4.4"
    const val stately = "1.1.0"
    const val serialization = "1.0.0"
    const val kotlinxDateTime = "0.1.1"

    object JVM {
        object Java {
            val source = JavaVersion.VERSION_1_8
            val target = JavaVersion.VERSION_1_8
        }
        object Kotlin {
            val target = JavaVersion.VERSION_1_8.toString()
        }
    }

    object Common {
        const val uuid = "0.2.3"
        const val logback = "1.2.3" //https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    }

    object AndroidX {
        const val appcompat = "1.2.0"
        const val constraintlayout = "2.0.4"
        const val core = "1.3.2"
        const val lifecycle = "2.2.0"
        const val recyclerview = "1.1.0"

        /**
         * USING: https://stackoverflow.com/a/66828584/5279996
         * Compiler version:
         *  . https://github.com/android/compose-samples/tags
         *  . https://maven.google.com/web/index.html#androidx.compose.compiler:compiler
         */
        const val jetpackCompose = "1.0.0-beta03"
    }

    object AndroidXTest {
        const val test = "1.3.0"
        const val testExt = "1.1.2"
        const val espresso = "3.3.0"
    }

    object OkHttp {
        const val okhttp3 = "4.9.0"
    }

}