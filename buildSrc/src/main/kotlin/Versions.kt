/**
 * Abbreviation: https://www.abbreviations.com/abbreviation/Version
 */
object V {
    const val minSdk = 24
    const val targetSdk = 30
    const val compileSdk = 30
    const val buildToolsVersion = "31.0.0 rc1"


    const val kotlin = "1.4.31"
    const val androidGradlePlugin = "7.0.0-alpha07"

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

    object Android {

        object CompileOptions {
//            const val sourceCompatibility = JavaVersion.VERSION_11
//            const val targetCompatibility = JavaVersion.VERSION_11
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
         * https://github.com/android/compose-samples/tags
         */
        const val jetpackCompose = "1.0.0-alpha12"
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