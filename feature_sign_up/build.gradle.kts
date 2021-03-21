plugins {
    id("com.itdevexpert.mobius.android-library")
}

android {

    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }

   /* buildFeatures {
//        Enables Jetpack Compose for this module
        compose = true
    }*/

    kotlinOptions {
        jvmTarget = V.JVM.Kotlin.target
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":kmm_shared:feature_sign_up"))

//    TODO: Refactor to :androidApp:domain
    implementation(project(":domain"))
    implementation(project(":shared"))
//    api(project(":view"))
}
