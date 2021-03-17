plugins {
//    id("com.itdevexpert.mobius.android-library")
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {

    compileSdkVersion(30)
    buildToolsVersion("31.0.0 rc1")

    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
//        Enables Jetpack Compose for this module
        compose = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        useIR = true
    }

//    TODO: Use this when classpath will be 'com.android.tools.build:gradle:7.0.0-alpha02' or above
    composeOptions {
        kotlinCompilerVersion = "1.4.31"
        kotlinCompilerExtensionVersion = V.AndroidX.jetpackCompose
    }
}

dependencies {
    api(D.Google.material)

//    implementation("androidx.compose.compiler:compiler:1.0.0-beta01")
//    api(D.AndroidX.JetpackCompose.compiler)  //TODO: Delete this when classpath will be 7.0.0-alpha02' or above
    api(D.AndroidX.JetpackCompose.ui)
    api(D.AndroidX.JetpackCompose.uiTooling)
    api(D.AndroidX.JetpackCompose.foundation)
    api(D.AndroidX.JetpackCompose.material)
    api(D.AndroidX.JetpackCompose.materialIconsCore)
    api(D.AndroidX.JetpackCompose.materialIconsExtended)
    api(D.AndroidX.JetpackCompose.activityCompose)
    api(D.AndroidX.JetpackCompose.lifecycleViewModelCompose)
    api(D.AndroidX.JetpackCompose.runtimeLivedata)
    api(D.AndroidX.JetpackCompose.runtimeRxJava2)

//    Testing
    androidTestImplementation(D.AndroidX.JetpackCompose.AndroidTest.uiTestJunit4)
}