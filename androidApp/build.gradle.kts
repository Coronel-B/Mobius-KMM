//https://stackoverflow.com/a/59708172/5279996
plugins {
    id("com.android.application")
    id("kotlin-android")
}

//https://stackoverflow.com/a/58574943/5279996
android {
    compileSdkVersion(30)
    buildToolsVersion("31.0.0 rc1")

    /**
     * Notes Impl: Use DomainObjectCollection#all
     * Source: https://stackoverflow.com/a/58035977/5279996
     */
    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName = "Möbius AI - ${variant.baseName} - ${variant.versionName} ${variant.versionCode}"
                println("OutputFileName: $outputFileName")
                output.outputFileName = outputFileName
            }
    }

    defaultConfig {
        applicationId = "app.mobius"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "0.0.1-2021.07.10-1"
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }
    buildTypes {
        getByName("debug") {
            
        }
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
        useIR = true
    }

    buildFeatures {
//        Enables Jetpack Compose for this module
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = V.AndroidX.Compose.jetpackCompose
    }

    /**
     * https://stackoverflow.com/q/62302913/5279996
     */
    packagingOptions {
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/DEPENDENCIES.txt")
        exclude("META-INF/*.kotlin_module")
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

//    implementation(project(':kmm_shared'))

//    ----- BEGIN ANDROID CORE -----
//    Welcome Feature
    implementation(project(":androidApp:feature-welcome:open"))
    implementation(project(":androidApp:feature-welcome:impl-wiring"))

//    SignUp Feature
    implementation(project(":androidApp:feature-sign-up:open"))
    implementation(project(":androidApp:feature-sign-up:impl-wiring"))

    api(project(":androidApp:shared"))
    api(project(":androidApp:domain"))
    api(project(":androidApp:view"))
//    ----- END ANDROID CORE -----


    implementation(D.Jetbrains.kotlin)

    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.cardview:cardview:1.0.0")


    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation("com.google.guava:guava:${V.guavaVersion}")
    implementation("com.google.code.gson:gson:${V.gson}")

    implementation("com.github.bumptech.glide:glide:${V.glideVersion}")

    implementation("de.hdodenhof:circleimageview:${V.circleImageViewVersion}")

    implementation("com.squareup.retrofit2:retrofit:${V.retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${V.retrofitVersion}")

    implementation("com.jakewharton.threetenabp:threetenabp:${V.threetenabp}")

    implementation("pub.devrel:easypermissions:${V.easypermissions}")

    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.google.android.gms:play-services-maps:17.0.0")

    testImplementation("junit:junit:4.13.1")
}

repositories {
    mavenCentral()
}
