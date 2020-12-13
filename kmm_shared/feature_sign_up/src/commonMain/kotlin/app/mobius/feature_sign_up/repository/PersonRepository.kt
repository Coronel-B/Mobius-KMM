package app.mobius.feature_sign_up.repository

import app.mobius.domain.Person
import io.ktor.client.statement.*

interface PersonRepository {
    suspend fun addPerson(person: Person) : HttpResponse
}
