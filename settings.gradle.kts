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

include ':androidApp'
include ':old-shared'

include ':feature_sign_up'
include ':feature_login'

include ':domain'
include ':view'
include ':iosApp'
include ':shared'

// Features
include(":androidApp:feature_sign_up")
include(":androidApp:feature_login")
