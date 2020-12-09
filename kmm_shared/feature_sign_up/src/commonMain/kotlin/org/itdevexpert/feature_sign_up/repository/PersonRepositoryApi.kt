package org.itdevexpert.feature_sign_up.repository

import app.mobius.network.engines.KtorHttpCLient
import io.ktor.util.*

@KtorExperimentalAPI
class PersonRepositoryApi : PersonRepository {

    val client = KtorHttpCLient().client

    override suspend fun getPerson(): String {
//          TODO
    }

}