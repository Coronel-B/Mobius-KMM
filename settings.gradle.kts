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
include(":kmm_shared")

// ------- BEGIN ANDROID --------

// Core
include(":androidApp:shared")
include(":androidApp:domain")
include(":androidApp:view")

// Features
include(":androidApp:feature_sign_up")
include(":androidApp:feature_login")

// ------- END ANDROID --------


include(":kmm_shared:network_api")
include(":kmm_shared:data_access")
include(":kmm_shared:cryptographic_storage")
include(":kmm_shared:file_managment")
include(":kmm_shared:credential_managment")
include(":kmm_shared:domain")
