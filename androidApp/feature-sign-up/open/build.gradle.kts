plugins {
    id("com.itdevexpert.mobius.android-library")
}

android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }

    kotlinOptions {
        jvmTarget = V.JVM.Kotlin.target
        useIR = true    //TODO: Check if is necessary
    }

    buildFeatures {
//        Enables Jetpack Compose for this module
        compose = true
    }

//    TODO: Migrate to some compose plugin
    composeOptions {
        kotlinCompilerExtensionVersion = V.AndroidX.Compose.jetpackCompose
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":androidApp:domain"))
    implementation(project(":androidApp:shared"))
    implementation(project(":androidApp:view"))
}