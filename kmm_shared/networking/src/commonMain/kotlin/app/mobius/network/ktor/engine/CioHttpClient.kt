package app.mobius.network.ktor.engine

import io.ktor.client.*
import io.ktor.client.engine.cio.*

@Suppress("EXPERIMENTAL_API_USAGE")
class CioHttpClient {

    /**
     * Build http client with CIO engine
     */
    fun buildCioHttpClient() : HttpClient {
        return HttpClient(CIO) {

        }
    }

}