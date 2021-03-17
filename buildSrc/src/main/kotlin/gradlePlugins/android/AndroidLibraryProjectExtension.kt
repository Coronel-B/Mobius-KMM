package gradlePlugins.android

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

private typealias AndroidBaseExtension = BaseExtension
private typealias AndroidBasePlugin = BasePlugin

/**
 * Note: You can still override properties which are set by our project plugin, by just configuring them again using the android {} block!
 * Source:
 *  . For take AndroidBaseExtension: https://github.com/goutham106/GmGradlePlugin/blob/master/buildSrc/src/main/kotlin/com/gm/gmgradle/Android.kt
 */
internal fun Project.configureAndroid() = this.extensions.getByType<AndroidBaseExtension>().run {
    compileSdkVersion(30)
    buildToolsVersion("31.0.0 rc1")
    defaultConfig {
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isTestCoverageEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

internal fun Project.configureDependencies() = dependencies {
    configureDependenciesOfTesting()
}

internal fun Project.configureDependenciesOfCore() = dependencies {
    val impl = "implementation"

    if (project.containsAndroidPlugin()) {
        add(impl, D.Jetbrains.kotlin)
        add(impl, D.AndroidX.appcompat)
        add(impl, D.AndroidX.coreKtx)
    }
}

internal fun Project.configureDependenciesOfTesting() = dependencies {
    val androidTestImplementation = "androidTestImplementation"
    val testImplementation = "testImplementation"

    add(testImplementation, D.junit)

    if (project.containsAndroidPlugin()) {
        add(androidTestImplementation, D.AndroidXTest.junit)
        add(androidTestImplementation, D.AndroidXTest.runner)
        add(androidTestImplementation, D.AndroidXTest.rules)
        add(androidTestImplementation, D.AndroidXTest.espresso)
    }
}

internal fun Project.containsAndroidPlugin(): Boolean {
    return project.plugins.toList().any { plugin -> plugin is AndroidBasePlugin }
}

internal fun Project.configurePlugins() {
    /**
     * Impl Notes: Always apply first com.android.library
     */
    plugins.apply("com.android.library")
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-parcelize")
}

