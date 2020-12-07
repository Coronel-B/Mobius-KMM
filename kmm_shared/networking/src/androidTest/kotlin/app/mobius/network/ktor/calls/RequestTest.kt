package app.mobius.network.ktor.calls

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