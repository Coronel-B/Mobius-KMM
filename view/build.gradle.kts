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

    /*buildFeatures {
//        Enables Jetpack Compose for this module
        compose = true
    }*/

    /**
     * TODO: Delete compose.runtime and compose.compiler dependencies and enable the following options
     * The 1.0.0-beta01 does not working
     */
    /*composeOptions {
        kotlinCompilerVersion = "1.4.30"
        kotlinCompilerExtensionVersion = V.AndroidX.jetpackCompose
    }*/
}
dependencies {
    api(D.Google.material)

    /**
     * https://stackoverflow.com/a/64485894/5279996
     * https://maven.google.com/web/index.html#androidx.compose.compiler:compiler
     */
//    implementation("androidx.compose.runtime:runtime:${V.AndroidX.jetpackCompose}")
//    implementation("androidx.compose.compiler:compiler:${V.AndroidX.jetpackCompose}")
//    implementation("androidx.ui:ui-tooling:${V.AndroidX.jetpackCompose}")

    /*api(D.AndroidX.JetpackCompose.ui)
    api(D.AndroidX.JetpackCompose.uiTooling)
    api(D.AndroidX.JetpackCompose.foundation)
    api(D.AndroidX.JetpackCompose.material)
    api(D.AndroidX.JetpackCompose.materialIconsCore)
    api(D.AndroidX.JetpackCompose.materialIconsExtended)
    api(D.AndroidX.JetpackCompose.activityCompose)
    api(D.AndroidX.JetpackCompose.lifecycleViewModelCompose)
    api(D.AndroidX.JetpackCompose.runtimeLivedata)
    api(D.AndroidX.JetpackCompose.runtimeRxJava2)*/

//    Testing
    androidTestImplementation(D.AndroidX.JetpackCompose.AndroidTest.uiTestJunit4)
}