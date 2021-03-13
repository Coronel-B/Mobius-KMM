/*plugins {
    id("core-android-library")
}*/

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
}

repositories {

}

android {
    compileSdkVersion(30)
    buildToolsVersion("31.0.0 rc1")

    defaultConfig {
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
//        Enables Jetpack Compose for this module
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${V.kotlin}")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")

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
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation(D.AndroidX.JetpackCompose.AndroidTest.uiTestJunit4)
}