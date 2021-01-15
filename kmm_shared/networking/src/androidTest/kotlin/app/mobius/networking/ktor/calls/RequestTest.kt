package app.mobius.networking.ktor.calls

import app.mobius.networking.ktor.calls.Request
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

@KtorExperimentalAPI
class RequestTest {

    @Test
    fun getContent() {
        println("---> Start")

        runBlocking {
            println("Success: " + Request().getHtmlContent())
        }

        println("---> Stop")
    }

}