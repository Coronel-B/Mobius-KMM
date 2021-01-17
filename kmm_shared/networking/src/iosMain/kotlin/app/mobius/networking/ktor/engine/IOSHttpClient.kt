package app.mobius.networking.ktor.engine

import io.ktor.client.*
import io.ktor.client.engine.ios.*

class IOSHttpClient {

    val client = HttpClient(Ios) {
        /**
         * Configure native NSUrlRequest.
         */
        /*configureRequest { // this: NSMutableURLRequest
            setAllowsCellularAccess(true)
            // ...
        }*/
    }

}