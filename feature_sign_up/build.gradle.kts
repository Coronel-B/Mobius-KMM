plugins {
//    id("com.itdevexpert.mobius.android-library")
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("31.0.0 rc1")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = V.JVM.Java.source
        targetCompatibility = V.JVM.Java.target
    }

    kotlinOptions {
        jvmTarget = V.JVM.Kotlin.target
//        useIR = true
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":kmm_shared:feature_sign_up"))    //TODO: Error Jacoco

//    TODO: Refactor to :androidApp:domain
    implementation(project(":domain"))
    implementation(project(":shared"))
//    api(project(":view"))
}
