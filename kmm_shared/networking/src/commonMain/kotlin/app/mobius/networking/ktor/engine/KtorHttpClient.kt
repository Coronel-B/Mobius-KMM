package app.mobius.networking.ktor.engine

import app.mobius.credential_managment.config.Config.MOBIUS_KMM_AUTHORIZATION_DEVELOPER_FULL_NAME
import app.mobius.credential_managment.config.Config.MOBIUS_KMM_AUTHORIZATION_DEVELOPER_SECRET_API_KEY
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

/**
 * Ktor HTTP Client has a common interface but allows to specify an engine that processes the
 * network request. Different engines have different configurations, dependencies and supporting
 * features.
 */
class KtorHttpClient {

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

            /**
             * Enable sendWithoutRequest: https://stackoverflow.com/a/61434078/5279996
             */
            basic {
                sendWithoutRequest = true
                username = MOBIUS_KMM_AUTHORIZATION_DEVELOPER_FULL_NAME
                password = MOBIUS_KMM_AUTHORIZATION_DEVELOPER_SECRET_API_KEY
            }

//            For Login feature
//            digest {  }

        }

        /**
         * https://ktor.io/docs/json-feature.html#kotlinx
         */
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
            })
        }

        defaultRequest {
      /*      method = HttpMethod.Head
            host = "http://localhost"
            port = 8090*/


            header("Content-Type", "application/vnd.api+json")
            header("Platform-Name", "Android")
            header("Platform-Ecosystem", "Mobile")
        }


//        TODO: Install Logging

        /**
         * . request timeout — time-bound of the whole request processing,
         * . connect timeout — time-bound of the connection establishment,
         * . socket timeout — time-bound of the interval between any two subsequent packets (read/write timeout).
         */
        install(HttpTimeout) {
//            timeout config
            requestTimeoutMillis = 30000
        }

    }

    /**
     * Source: https://ktor.io/docs/default-request.html#example
     */
    private fun installDefaultHeaders() {
    }


}