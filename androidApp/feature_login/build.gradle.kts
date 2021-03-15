plugins {
    id("com.itdevexpert.mobius.androidLibrary")
}

android {

    defaultConfig {
        versionCode 1
        versionName "1.0"
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}