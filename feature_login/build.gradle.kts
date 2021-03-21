plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
//    id("com.itdevexpert.mobius.android-library")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("31.0.0 rc1")

    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }


    kotlinOptions {
        jvmTarget = V.JVM.Kotlin.target
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}