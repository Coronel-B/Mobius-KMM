package app.mobius.network.ktor.calls

import app.mobius.networking.ktor.engine.KtorHttpCLient
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

@KtorExperimentalAPI
class RequestAndroid {

    private val client = KtorHttpCLient().client

    suspend fun getHtmlContent() = client.request<String> {
        url("http://itdevexpert.com")
        method = HttpMethod.Get
    }

}