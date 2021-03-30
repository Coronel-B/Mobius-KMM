plugins {
    id("com.itdevexpert.mobius.android-library")
}

android {

    defaultConfig {
        versionCode = 1
        versionName = "0.0"
    }

    kotlinOptions {
        jvmTarget = V.JVM.Kotlin.target
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":kmm_shared:feature_sign_up"))

    implementation(project(":androidApp:domain"))
    implementation(project(":androidApp:shared"))
    implementation(project(":androidApp:view"))
}