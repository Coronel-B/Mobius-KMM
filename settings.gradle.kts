pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:4.0.2")
            }

        }
    }
}
rootProject.name = "Mobius"

include(":androidApp")
include(":iosApp")
include(":shared_kmm")

// ------- BEGIN ANDROID --------

// Core
include(":androidApp:shared")
include(":androidApp:domain")
include(":androidApp:view")

// Features
include(":androidApp:feature_sign_up")
include(":androidApp:feature_login")

// ------- END ANDROID --------





