package plugin.android

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    /*kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }*/
}

internal fun Project.configureDependencies() = dependencies {
    add("testImplementation", D.junit)

    val androidTestImplementation = "androidTestImplementation"
    if (project.containsAndroidPlugin()) {
        add(androidTestImplementation, D.AndroidXTest.runner)
        add(androidTestImplementation, D.AndroidXTest.rules)
        add(androidTestImplementation, D.AndroidTest.espresso)
    }
}

internal fun Project.containsAndroidPlugin(): Boolean {
    return project.plugins.toList().any { plugin -> plugin is AndroidBasePlugin }
}

internal fun Project.configurePlugins() {
    plugins.apply("com.android.library")
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-parcelize")
}

