import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.codingfeline.buildkonfig.gradle.TargetConfigDsl

/**
 * Describes the appAuthorizationKey from home properties on build time
 * PRECONDITION: Variables exist as property in ~/.gradle/gradle.properties with <YOUR-KEY>
 *
 * TODO: Migrate to a task
 * TODO: do a task for addEditKeyAppAuthorizationDeveloper
 *
 * Source:
 *  . Get variable with DSL: https://stackoverflow.com/a/59871066/5279996
 *  . https://stackoverflow.com/a/46805257/5279996
 */
val appAuthorizationDeveloperFullName = project.properties["mobiusKmmProperty_appAuthorizationDeveloperFullName"].toString()
val mobiusKmmProperty_appAuthorizationDeveloperSecretApiKey: String by project


/**
 * BuildKonfig: https://plugins.gradle.org/plugin/com.codingfeline.buildkonfig
 */
plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.codingfeline.buildkonfig") version "0.7.0"
}

/**
 * Using:
 *      $ ./gradlew generateBuildKonfig
 *
 * Source: https://github.com/yshrsmz/BuildKonfig/issues/41
 * Issue: https://github.com/yshrsmz/BuildKonfig/issues/44
 */
buildkonfig {
    packageName = "app.mobius.credential_managment"

    // default config is required
    defaultConfigs {
        buildConfigField(
            STRING, "MOBIUS_KMM_AUTHORIZATION_DEVELOPER_FULL_NAME",
            "$appAuthorizationDeveloperFullName"
        )
        buildConfigField(
            STRING, "MOBIUS_KMM_AUTHORIZATION_DEVELOPER_SECRET_API_KEY",
            "$mobiusKmmProperty_appAuthorizationDeveloperSecretApiKey"
        )
    }

    targetConfigs(closureOf<NamedDomainObjectContainer<TargetConfigDsl>> {
        create("android") {}
        create("ios") { }
    })

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
        minSdkVersion(24)
        targetSdkVersion(30)
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

}

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