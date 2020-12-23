package app.mobius.credential_managment

import app.mobius.credential_managment.config.GLOBAL_PASSWORD_APPLICATION
import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDateTime

data class AppCredentials(
    val uuid: Uuid,
    val appGlobalSessionToken: AppGlobalSessionToken,
    val consumer: ConsumerPeople,
    val environment: Environment,
    val password: String = GLOBAL_PASSWORD_APPLICATION,
    val version: Double
)

enum class Environment {
    DEV, INTEGRATION, TESTING, STAGING, PRODUCTION
}

data class ConsumerPeople(
    val uuid: Uuid,
    val platform: Platform
)

/**
 * @param name: i.e: Android
 * @param ecosystem: i.e: Auto, TV, Wear OS
 */
data class Platform(
    val uuid: Uuid,
    val name: String,
    val ecosystem: String
)