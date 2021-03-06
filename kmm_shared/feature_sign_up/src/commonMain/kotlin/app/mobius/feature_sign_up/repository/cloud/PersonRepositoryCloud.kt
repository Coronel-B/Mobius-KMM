package app.mobius.feature_sign_up.repository.cloud

import app.mobius.feature_sign_up.api.SignUpEndpoints
import app.mobius.feature_sign_up.domain.dto.PersonSignUpDto
import app.mobius.networking.ktor.engine.KtorHttpClient
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import app.mobius.feature_sign_up.repository.PersonRepository
import io.ktor.client.statement.*

@KtorExperimentalAPI
class PersonRepositoryCloud : PersonRepository {

    private val client = KtorHttpClient().client

    override suspend fun addPerson(personSignUpDto: PersonSignUpDto) : HttpResponse {
        return client.post {
            addPersonResult(SignUpEndpoints.Companion.Path.ADD_PERSON)
        }
    }

    private fun HttpRequestBuilder.addPersonResult(path: String) {
        url {
            encodedPath = path
        }
        body = PersonSignUpDto(username = "itdev")
    }

}