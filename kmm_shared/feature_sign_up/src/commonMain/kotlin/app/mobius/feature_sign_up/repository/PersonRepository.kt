package app.mobius.feature_sign_up.repository

import app.mobius.feature_sign_up.domain.dto.PersonSignUpDto
import io.ktor.client.statement.*

interface PersonRepository {
    suspend fun addPerson(personSignUpDto: PersonSignUpDto) : HttpResponse
}
