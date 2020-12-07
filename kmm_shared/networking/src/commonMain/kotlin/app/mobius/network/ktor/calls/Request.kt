package app.mobius.network.ktor.calls

import app.mobius.network.engines.KtorHttpCLient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

/**
 *  When handling routes (Routing feature), or directly intercepting the pipeline (requests),
 *  you get a context with an
 *  ApplicationCall. That call contains a property called request.
 *  The main function for creating HTTP requests is request. All the request settings are generated
 *  using the HttpRequestBuilder.
 *  The request function has the suspend modifier, so requests can be executed in coroutines.
 */
class Request {

    @KtorExperimentalAPI
    val client = KtorHttpCLient().client

    val htmlContent = client.request<String>() {
        url("http://itdevexpert.com")
        method = HttpMethod.Get

    }

}