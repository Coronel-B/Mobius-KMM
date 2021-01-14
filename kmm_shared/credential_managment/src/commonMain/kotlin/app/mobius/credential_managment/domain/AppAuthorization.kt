package app.mobius.credential_managment.domain

import com.benasher44.uuid.Uuid

//TODO: Keep uuid ?
sealed class AppAuthorization(
    open val uuid: Uuid? = null,
    val consumer: AppConsumer,
    val environment: Environment,
    val version: Double
) {
    data class Developer(
        val uuid: Uuid? = null,
        val appConsumerDeveloper: AppConsumer.Developer,

    )
}

sealed class AppConsumer(
    val platform: Platform
) {
    data class Developer(
        val uuid: Uuid,
        val password: String,
        val name: String
    )
}

enum class Environment {
    DEV, INTEGRATION, TESTING, STAGING, PRODUCTION
}

/**
 * @param name: i.e: Android
 * @param ecosystem: i.e: Auto, TV, Wear OS
 */
data class Platform(
    val uuid: Uuid,
    val name: String,
    val ecosystem: String
)
