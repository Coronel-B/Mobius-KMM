package org.itdevexpert.feature_sign_up.repository

import app.mobius.domain.Person

interface PersonRepository {
    suspend fun addPerson() : Person
}