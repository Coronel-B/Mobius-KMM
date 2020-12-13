package app.mobius.domain

import com.benasher44.uuid.Uuid

expect class Person(personUuid: Uuid?, username: String) {
    val personUuid: Uuid?
    val username: String
}