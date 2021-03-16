plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
//    id("com.itdevexpert.mobius.android-library.AndroidLibraryPlugin2")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("31.0.0 rc1")
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}