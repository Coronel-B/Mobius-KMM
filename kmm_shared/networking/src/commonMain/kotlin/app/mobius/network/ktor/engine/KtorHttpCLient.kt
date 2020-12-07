package app.mobius.network.engines

import io.ktor.client.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.observer.*
import io.ktor.util.*

/**
 * Ktor HTTP Client has a common interface but allows to specify an engine that processes the
 * network request. Different engines have different configurations, dependencies and supporting
 * features.
 */
class KtorHttpCLient {

    /**
     * Client Configuration
     */
    @KtorExperimentalAPI
    val client = HttpClient {

        /**
         * Set up an observer for responses
         * Receiving HTTP errors in response don’t cause exceptions.
         * A ResponseObserver is created that prints response statuses to the standard output
         */
        expectSuccess = false
        install(ResponseObserver) {
            onResponse {  response ->
                println("HTTP status: ${response.status.value}")
            }
        }

        /**
         * Engine Configuration
         * OBS: threadsCount property is a recommendation to use by an engine and can be ignored.
         */
        engine {
            threadsCount = 4
            pipelining = true
        }

        /**
         * Set up an observer for auth
         */
        install(Auth) {
//            Providers config
        }


    }


}