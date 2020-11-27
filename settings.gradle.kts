pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:4.2.0-alpha16")
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


include(":shared_kmm:network")
include(":shared_kmm:data_access")
include(":shared_kmm:cryptographic_storage")
