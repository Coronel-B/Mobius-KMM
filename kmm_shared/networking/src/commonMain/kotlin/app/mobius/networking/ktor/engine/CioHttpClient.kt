package app.mobius.networking.ktor.engine

import app.mobius.util.annotations.Unimplemented
import io.ktor.client.*
import io.ktor.client.engine.cio.*

@Unimplemented
@Deprecated("Unimplemented")
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