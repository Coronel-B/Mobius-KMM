package app.mobius.feature_sign_up.repository.cloud

import app.mobius.domain.Person
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

@KtorExperimentalAPI
class PersonRepositoryCloudTest {

    private val personRepositoryCloud = PersonRepositoryCloud()

    @Test
    fun addPerson() {
        val person = Person()
        runBlocking {
            val result = personRepositoryCloud.addPerson(person)
            assert(result.status.isSuccess())
        }
    }

}