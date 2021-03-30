package gradlePlugins.android

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

private const val IMPL = "implementation"
private const val ANDROID_TEST_IMPL = "androidTestImplementation"
private const val TEST_IMPL = "testImplementation"

private typealias AndroidBaseExtension = BaseExtension
private typealias AndroidBasePlugin = BasePlugin


internal fun Project.configurePlugins() {
    /**
     * Impl Notes: Always apply first com.android.library
     */
    plugins.apply("com.android.library")
    plugins.apply("kotlin-android")
//    plugins.apply("kotlin-parcelize")
}

/**
 * Note: You can still override properties which are set by our project plugin, by just configuring them again using the android {} block!
 * Source:
 *  . For take AndroidBaseExtension: https://github.com/goutham106/GmGradlePlugin/blob/master/buildSrc/src/main/kotlin/com/gm/gmgradle/Android.kt
 */
internal fun Project.configureAndroid() = this.extensions.getByType<AndroidBaseExtension>().run {
    compileSdkVersion(30)
    buildToolsVersion("31.0.0 rc1")

    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isTestCoverageEnabled = true
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = V.JVM.Java.source
        targetCompatibility = V.JVM.Java.target
    }
}

internal fun Project.configureDependencies() = dependencies {
    configureDependenciesOfCore()
    configureDependenciesOfTesting()
}

internal fun Project.configureDependenciesOfCore() = dependencies {
    if (project.containsAndroidPlugin()) {
        add(IMPL, D.Jetbrains.kotlin)
        add(IMPL, D.AndroidX.appcompat)
        add(IMPL, D.AndroidX.coreKtx)
    }
}

internal fun Project.configureDependenciesOfTesting() = dependencies {
    add(TEST_IMPL, D.junit)

    if (project.containsAndroidPlugin()) {
        add(ANDROID_TEST_IMPL, D.AndroidXTest.junit)
        add(ANDROID_TEST_IMPL, D.AndroidXTest.runner)
        add(ANDROID_TEST_IMPL, D.AndroidXTest.rules)
        add(ANDROID_TEST_IMPL, D.AndroidXTest.espresso)
    }
}

internal fun Project.containsAndroidPlugin(): Boolean {
    return project.plugins.toList().any { plugin -> plugin is AndroidBasePlugin }
}