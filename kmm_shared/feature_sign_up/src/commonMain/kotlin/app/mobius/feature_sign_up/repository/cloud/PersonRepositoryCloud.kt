package app.mobius.feature_sign_up.repository.cloud

import app.mobius.feature_sign_up.domain.dto.PersonSignUpDto
import app.mobius.network.engines.KtorHttpCLient
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import app.mobius.feature_sign_up.repository.PersonRepository
import io.ktor.client.statement.*

@KtorExperimentalAPI
class PersonRepositoryCloud : PersonRepository {

    private val client = KtorHttpCLient().client

    override suspend fun addPerson(personSignUpDto: PersonSignUpDto) : HttpResponse {
        return client.post {
            addPersonResult("people/add")
        }
    }

    private fun HttpRequestBuilder.addPersonResult(path: String) {
        url {
            takeFrom("http://localhost:8090/")
            encodedPath = path
        }
    }

}