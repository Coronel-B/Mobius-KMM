package app.mobius.network.ktor.engine

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

/**
 * Reason for use this client: https://stackoverflow.com/a/58585489/5279996
 */
class OkHt2tpClient {

    val client = HttpClient(OkHttp) {
        engine {
            // https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.Builder.html
            config { // this: OkHttpClient.Builder ->
                // ...
                followRedirects(true)
                // ...
            }

            // https://square.github.io/okhttp/3.x/okhttp/okhttp3/Interceptor.html
//            addInterceptor(interceptor)
//            addNetworkInterceptor(interceptor)

            /**
             * Set okhttp client instance to use instead of creating one.
             */
//            preconfigured = okHttpClientInstance
        }
    }

}