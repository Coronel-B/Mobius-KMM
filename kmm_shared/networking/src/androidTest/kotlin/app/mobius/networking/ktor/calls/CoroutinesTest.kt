package app.mobius.networking.ktor.calls

import io.ktor.util.*
import kotlinx.coroutines.*
import org.junit.Test

@KtorExperimentalAPI
class CoroutinesTest {

    /*@Test
    fun millionThreads() {
        val c = AtomicLong()

        for (i in 1..1_000_000L)
            thread(start = true) {
                c.addAndGet(i)
                println(i)
            }

        println(c.get())
    }*/

    @Test
    fun millionCoroutines() {
        val deferred = (1..1_000_000).map {
            GlobalScope.async {
                it
            }
        }

        runBlocking {
//            All these have already started, all we need is take every coroutine and await its result here
            val sum = deferred.sumOf { it.await().toLong() }
            println("Sum: $sum")    // Return the sum of the consecutive
            /*deferred.map {
                println(it.await())
            }*/
        }
    }

}