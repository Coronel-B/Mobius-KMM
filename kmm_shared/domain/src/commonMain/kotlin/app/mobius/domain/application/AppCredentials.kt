package app.mobius.domain.application

import com.benasher44.uuid.Uuid

data class AppCredentials(
    val uuid: Uuid,
    val environment: Environment,
    val consumer: Consumer,
    val publicKey: String   //TODO: Use private key or (user and pw)?
)

enum class Environment {
    DEV, INTEGRATION, TESTING, STAGING, PRODUCTION
}

sealed class Consumer {
    data class ConsumerIdentities(
        val uuid: Uuid,
        val platform: Platform) : Consumer()

    /**
     * A partner consumes a particular feature
     */
    data class ConsumerPartner(
        val uuid: Uuid,
        val name: String,
        val platform: Platform,
        val feature: String) : Consumer()

    /**
     * A team consumes a particular feature
     */
    data class ConsumerTeam(
        val uuid: Uuid,
        val name: String,
        val platform: Platform,
        val feature: String) : Consumer()
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