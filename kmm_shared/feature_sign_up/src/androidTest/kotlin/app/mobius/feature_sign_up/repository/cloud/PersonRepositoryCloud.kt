package app.mobius.feature_sign_up.repository.cloud

import app.mobius.feature_sign_up.domain.dto.PersonSignUpDto
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

@KtorExperimentalAPI
class PersonRepositoryCloudTest {

    private val personRepositoryCloud = PersonRepositoryCloud()

    @Test
    fun addPerson() {
        val person = PersonSignUpDto(
            username = "itdev"
        )
        runBlocking {
            val result = personRepositoryCloud.addPerson(person)
            assert(result.status.isSuccess())
        }
    }

}