/**
 * TODO Impl: https://developer.android.com/studio/write/lint
 * Acronym: https://www.acronymfinder.com/Dependency-(D).html
 */

object D {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${V.androidGradlePlugin}"
    const val cocoapodsext = "co.touchlab:kotlinnativecocoapods:${V.cocoapodsext}"
    const val junit = "junit:junit:${V.junit}"
    const val material = "com.google.android.material:material:${V.material}"
    const val karmok = "co.touchlab:karmok-library:${V.karmok}"
    const val kermit = "co.touchlab:kermit:${V.kermit}"
    const val multiplatformSettings = "com.russhwolf:multiplatform-settings:${V.multiplatformSettings}"
    const val multiplatformSettingsTest = "com.russhwolf:multiplatform-settings-test:${V.multiplatformSettings}"
    const val robolectric = "org.robolectric:robolectric:${V.robolectric}"
    const val stately = "co.touchlab:stately-common:${V.stately}"
    const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${V.kotlinxDateTime}"

    object AndroidX {
        private const val lifecycle = "androidx.lifecycle:lifecycle-"

        const val appcompat = "androidx.appcompat:appcompat:${V.AndroidX.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${V.AndroidX.core}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${V.AndroidX.constraintlayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${V.AndroidX.recyclerview}"
        const val lifecycleViewmodel = "${lifecycle}viewmodel:${V.AndroidX.lifecycle}"
        const val lifecycleViewmodelExtensions = "${lifecycle}viewmodel-ktx:${V.AndroidX.lifecycle}"
        const val lifecycleLivedata = "${lifecycle}livedata:${V.AndroidX.lifecycle}"
        const val lifecycleExtension = "${lifecycle}extensions:${V.AndroidX.lifecycle}"
    }

    object AndroidXTest {
        private const val test = "androidx.test"

        const val core = "${test}:core:${V.AndroidX.test}"
        const val junit = "${test}.ext:junit:${V.AndroidX.testExt}"
        const val runner = "${test}:runner:${V.AndroidX.test}"
        const val rules = "${test}:rules:${V.AndroidX.test}"
    }

    object KotlinTest {
        private const val kotlinTest = "org.jetbrains.kotlin:kotlin-test"

        const val common = "${kotlinTest}-common:${V.kotlin}"
        const val annotations = "${kotlinTest}-annotations-common:${V.kotlin}"
        const val jvm = "${kotlinTest}:${V.kotlin}"
        const val junit = "${kotlinTest}-junit:${V.kotlin}"
    }

    object Coroutines {
        private const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-"

        const val common = "${coroutine}core:${V.coroutines}"
        const val android = "${coroutine}android:${V.coroutines}"
        const val androidTest = "${coroutine}test:${V.coroutines}"
    }

    object SqlDelight {
        private const val sqldelight = "com.squareup.sqldelight:"

        const val gradle = "${sqldelight}gradle-plugin:${V.sqlDelight}"
        const val runtime = "${sqldelight}runtime:${V.sqlDelight}"
        const val runtimeJdk = "${sqldelight}runtime-jvm:${V.sqlDelight}"
        const val driverIos = "${sqldelight}native-driver:${V.sqlDelight}"
        const val driverAndroid = "${sqldelight}android-driver:${V.sqlDelight}"
    }

    object Ktor {
        private const val client = "io.ktor:ktor-client-"

        object Common {
            const val commonCore = "${client}core:${V.ktor}"
            const val commonMock = "${client}mock:${V.ktor}"
            const val commonCio = "${client}cio:${V.ktor}"

            const val commonJson = "${client}json:${V.ktor}"
            const val commonLogging = "${client}logging:${V.ktor}"
            const val commonSerialization = "${client}serialization:${V.ktor}"
            const val commonAuth = "${client}auth:${V.ktor}"
        }

        object Android {
            const val androidOkHttp = "${client}okhttp:${V.ktor}"
            const val androidMock = "${client}mock-jvm:${V.ktor}"

            const val androidLogging = "${client}logging-jvm:${V.ktor}"
            const val androidSerialization = "${client}serialization-jvm:${V.ktor}"
        }

        object iOS {
            const val iOSHttpClient = "${client}ios:${V.ktor}"
            const val iOSMock = "${client}mock-native:${V.ktorMockNative}"
        }

    }

}