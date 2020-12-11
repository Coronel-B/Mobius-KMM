package org.itdevexpert.feature_sign_up.repository.cloud

import app.mobius.domain.Person
import app.mobius.network.engines.KtorHttpCLient
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import org.itdevexpert.feature_sign_up.TestExpect
import org.itdevexpert.feature_sign_up.repository.PersonRepository

@KtorExperimentalAPI
class PersonRepositoryCloud : PersonRepository {

    private val client = KtorHttpCLient().client

//    TODO: Send a Person()
    override suspend fun addPerson(): Person {
        return client.get<Person> {
            addPersonResult("api/addPerson")
        }
    }

    private fun HttpRequestBuilder.addPersonResult(path: String) {
        url {
            takeFrom("http://itdevexpert.com/")
            encodedPath = path
        }
    }

}