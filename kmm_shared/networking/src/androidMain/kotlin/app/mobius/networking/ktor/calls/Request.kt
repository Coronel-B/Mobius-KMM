package app.mobius.networking.ktor.calls

import app.mobius.networking.ktor.engine.KtorHttpClient
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

@KtorExperimentalAPI
class RequestAndroid {

    private val client = KtorHttpClient().client

    suspend fun getHtmlContent() = client.request<String> {
        url("http://itdevexpert.com")
        method = HttpMethod.Get
    }

}