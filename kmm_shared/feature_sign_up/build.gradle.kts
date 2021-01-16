import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.4.10"
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "kmm_shared:feature_sign_up"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":kmm_shared:domain"))
                api(project(":kmm_shared:networking"))

                /**
                 * TODO | Gradle: Use the dependencies of the parent module (:networking)
                 * Using Clean Project does not work
                 */
                implementation(D.Ktor.Common.commonCore)
                implementation(D.Ktor.Common.commonSerialization)

                implementation(D.Common.uuid)
                implementation(D.Common.kotlinxDateTime)
            }
        }

        val androidMain by getting {
            dependencies {
            }
        }

        val iosMain by getting

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")

//                TODO | Gradle: Use the dependencies of the parent module (:networking)
                implementation(D.Ktor.Android.androidMock)
                implementation(D.Coroutines.androidTest)
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
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