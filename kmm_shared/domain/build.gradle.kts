import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

repositories {
    maven(url = "https://kotlin.bintray.com/kotlinx/") //TODO | Gradle: soon will be just jcenter()
}

kotlin {

    android()
    ios {
        binaries {
            framework {
                baseName = "kmm_shared:domain"
            }
        }
    }

    sourceSets {


        /**
         * UUID: https://stackoverflow.com/a/55426090/5279996
         * Date: https://github.com/Kotlin/kotlinx-datetime
         */
        val commonMain by getting {
            dependencies {
                implementation(D.Common.uuid)
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")
            }
        }

        val androidMain by getting {

        }

        val iosMain by getting {

        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.1")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(30)
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
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