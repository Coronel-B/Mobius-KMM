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
                useModule("com.android.tools.build:gradle:7.0.0-alpha07")
            }

        }
    }
}
rootProject.name = "Mobius"

include(":androidApp")
include(":kmm_shared")

// ------- BEGIN ANDROID --------

// Core
include(":shared")
include(":domain")
include(":view")

// Features
include(":feature_sign_up")
include(":feature_login")

// ------- END ANDROID --------


include(":kmm_shared:networking")
include(":kmm_shared:data_access")
include(":kmm_shared:cryptographic_storage")
include(":kmm_shared:file_managment")
include(":kmm_shared:credential_managment")
include(":kmm_shared:util")
include(":kmm_shared:feature_sign_up")
include(":kmm_shared:domain")