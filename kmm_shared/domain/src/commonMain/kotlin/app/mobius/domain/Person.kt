package app.mobius.domain

import com.benasher44.uuid.Uuid

expect class Person(personUUID: Uuid, username: String
) {
    val personUUID: Uuid
    val username: String
}