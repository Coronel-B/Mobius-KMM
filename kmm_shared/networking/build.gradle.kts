import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

android {
    compileSdkVersion(30)
    buildToolsVersion("31.0.0 rc1")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "kmm_shared:network"
            }
        }
    }

    version = "0.0"

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(project(":kmm_shared:util"))
                implementation(project(":kmm_shared:credential_managment"))

                implementation(D.Ktor.Common.commonCore)
                implementation(D.Ktor.Common.commonCio)
                implementation(D.Ktor.Common.commonLogging)
                implementation(D.Ktor.Common.logback)
                implementation(D.Ktor.Common.commonJson)
                implementation(D.Ktor.Common.commonSerialization)
                implementation(D.Ktor.Common.commonAuth)

                implementation(D.Coroutines.common)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(D.Ktor.Android.androidOkHttp)

                implementation(D.Coroutines.android)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(D.Ktor.iOS.iOSHttpClient)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                implementation(D.Ktor.Common.commonMock)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.1")

                implementation(D.Ktor.Android.androidMock)
                implementation(D.Coroutines.androidTest)
            }
        }
        val iosTest by getting {
            dependencies {
                implementation(D.Ktor.iOS.iOSMock)
            }
        }
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework =
        kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)