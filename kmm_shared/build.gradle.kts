import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
}
group = "app.mobius.myapplication"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}

android {
    compileSdkVersion(30)
    buildToolsVersion("31.0.0 rc1")
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
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

    /**
     * Workaround of Fixed issue in Kotlin 1.5 M1
     * PRECONDITION: These configurations have to be done in all modules / sub-modules
     * of the project that are KMM and the android {} block has to be before the kotlin {} block
     * TODO: Delete workaround for issue/KT-43944
     * Source:
     *  . https://stackoverflow.com/a/66433024/5279996
     *  . https://youtrack.jetbrains.com/issue/KT-43944#focus=Comments-27-4736409.0-0
     */
//    configurations {
        /*create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")*/

        /*create("testApi") {}
        create("testDebugApi") {}
        create("testReleaseApi") {}*/
//    }
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "kmm_shared"
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.3.2")
                implementation("com.google.android.material:material:1.2.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.1")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)