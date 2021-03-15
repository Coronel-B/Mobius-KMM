plugins {
    id("com.itdevexpert.mobius.androidLibrary")
}

android {

    defaultConfig {
        versionCode 1
        versionName "1.0"
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