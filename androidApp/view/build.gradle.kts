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

    buildFeatures {
//        Enables Jetpack Compose for this module
        compose = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

//    TODO: Use this when classpath will be 'com.android.tools.build:gradle:7.0.0-alpha02' or above
    /*composeOptions {
        kotlinCompilerVersion = "1.4.31"
        kotlinCompilerExtensionVersion = V.AndroidX.jetpackCompose
    }*/
}

dependencies {
    implementation(D.Google.material)

    implementation(D.AndroidX.JetpackCompose.compiler)  //TODO: Delete this when classpath will be 7.0.0-alpha02' or above
    implementation(D.AndroidX.JetpackCompose.ui)
    implementation(D.AndroidX.JetpackCompose.uiTooling)
    implementation(D.AndroidX.JetpackCompose.foundation)
    implementation(D.AndroidX.JetpackCompose.material)
    implementation(D.AndroidX.JetpackCompose.materialIconsCore)
    implementation(D.AndroidX.JetpackCompose.materialIconsExtended)
    implementation(D.AndroidX.JetpackCompose.activityCompose)
    implementation(D.AndroidX.JetpackCompose.lifecycleViewModelCompose)
    implementation(D.AndroidX.JetpackCompose.runtimeLivedata)
    implementation(D.AndroidX.JetpackCompose.runtimeRxJava2)

//    Testing
    androidTestImplementation(D.AndroidX.JetpackCompose.AndroidTest.uiTestJunit4)
}