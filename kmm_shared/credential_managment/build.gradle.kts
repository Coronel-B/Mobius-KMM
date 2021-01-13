import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

/**
 * Describes the appAuthorizationKey from home properties on build time
 * PRECONDITION: Add appAuthorizationKey property in ~/.gradle/gradle.properties with <YOUR-KEY>
 *
 * TODO: Migrate to a task
 * TODO: do a task for addEditKeyAppAuthorizationDeveloper
 *
 * Source:
 *  . Get variable with DSL: https://stackoverflow.com/a/59871066/5279996
 *  . https://stackoverflow.com/a/46805257/5279996
 */
/*val mobiusKmmProperty_KeyAppAuthorizationDeveloper: String by project
println(mobiusKmmProperty_KeyAppAuthorizationDeveloper)*/

// --------------------

/*buildScript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:+")
    }
}*/

plugins {
    kotlin("multiplatform")
    id("com.android.library")
//    id("com.codingfeline.buildkonfig")
}



/*buildkonfig {
    packageName("com.codingfeline.buildkonfigsample")

    defaultConfigs {
        buildConfigField("STRING", "test", "testvalue")
    }
}*/

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "kmm_shared:credential_managment"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(D.Common.uuid)
                implementation(D.Common.kotlinxDateTime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting
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