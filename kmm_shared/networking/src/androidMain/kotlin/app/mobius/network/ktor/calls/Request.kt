package app.mobius.network.ktor.calls

import app.mobius.network.engines.KtorHttpCLient
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@KtorExperimentalAPI
class Request {

    private val client = KtorHttpCLient().client

    suspend fun getHtmlContent() = client.request<String> {
        url("http://itdevexpert.com")
        method = HttpMethod.Get
    }

}

@KtorExperimentalAPI
object TestRequest {

    @JvmStatic
    fun main(args: Array<String>) {
        /*GlobalScope.launch {
            println(Request().getHtmlContent())
        }*/
    }

}