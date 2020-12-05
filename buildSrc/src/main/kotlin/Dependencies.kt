/**
 * TODO Impl: https://developer.android.com/studio/write/lint
 * Acronym: https://www.acronymfinder.com/Dependency-(D).html
 */
object D {
    const val android_gradle_plugin = "com.android.tools.build:gradle:${V.androidGradlePlugin}"
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
        const val appcompat = "androidx.appcompat:appcompat:${V.AndroidX.appcompat}"
        const val core_ktx = "androidx.core:core-ktx:${V.AndroidX.core}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${V.AndroidX.constraintlayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${V.AndroidX.recyclerview}"
        const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel:${V.AndroidX.lifecycle}"
        const val lifecycle_viewmodel_extensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${V.AndroidX.lifecycle}"
        const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata:${V.AndroidX.lifecycle}"
        const val lifecycle_extension = "androidx.lifecycle:lifecycle-extensions:${V.AndroidX.lifecycle}"
    }

    object AndroidXTest {
        const val core = "androidx.test:core:${V.AndroidX.test}"
        const val junit = "androidx.test.ext:junit:${V.AndroidX.testExt}"
        const val runner = "androidx.test:runner:${V.AndroidX.test}"
        const val rules = "androidx.test:rules:${V.AndroidX.test}"
    }

    object KotlinTest {
        const val common = "org.jetbrains.kotlin:kotlin-test-common:${V.kotlin}"
        const val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${V.kotlin}"
        const val jvm = "org.jetbrains.kotlin:kotlin-test:${V.kotlin}"
        const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${V.kotlin}"
    }

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${V.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${V.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${V.coroutines}"
    }

    object SqlDelight {
        const val gradle = "com.squareup.sqldelight:gradle-plugin:${V.sqlDelight}"
        const val runtime = "com.squareup.sqldelight:runtime:${V.sqlDelight}"
        const val runtimeJdk = "com.squareup.sqldelight:runtime-jvm:${V.sqlDelight}"
        const val driverIos = "com.squareup.sqldelight:native-driver:${V.sqlDelight}"
        const val driverAndroid = "com.squareup.sqldelight:android-driver:${V.sqlDelight}"
    }

    object Ktor {
        const val commonCore = "io.ktor:ktor-client-core:${V.ktor}"
        const val commonJson = "io.ktor:ktor-client-json:${V.ktor}"
        const val commonLogging = "io.ktor:ktor-client-logging:${V.ktor}"
        const val commonSerialization = "io.ktor:ktor-client-serialization:${V.ktor}"

        const val androidCore = "io.ktor:ktor-client-okhttp:${V.ktor}"

        const val iOS = "io.ktor:ktor-client-ios:${V.ktor}"
    }
}