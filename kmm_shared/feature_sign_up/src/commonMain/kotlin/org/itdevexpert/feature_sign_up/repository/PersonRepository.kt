package org.itdevexpert.feature_sign_up.repository

interface PersonRepository {

    suspend fun getPerson() : String    //TODO: Ktor

}