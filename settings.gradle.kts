pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
}
rootProject.name = "Mobius"

include(":androidApp")
include(":kmm_shared")

// ------- BEGIN ANDROID --------

// Core
include(":androidApp:shared")
include(":androidApp:domain")
include(":androidApp:view")

// Features
include(":androidApp:feature-welcome:open")
include(":androidApp:feature-welcome:impl")

include(":androidApp:feature-sign-up:open")
include(":androidApp:feature-sign-up:impl")
include(":androidApp:feature-sign-up:impl-wiring")

include(":androidApp:feature_login")

// ------- END ANDROID --------


include(":kmm_shared:networking")
include(":kmm_shared:data_access")
include(":kmm_shared:cryptographic_storage")
include(":kmm_shared:file_managment")
include(":kmm_shared:credential_managment")
include(":kmm_shared:util")
include(":kmm_shared:feature_sign_up")
include(":kmm_shared:domain")
include(":androidApp:feature-welcome:impl-wiring")
