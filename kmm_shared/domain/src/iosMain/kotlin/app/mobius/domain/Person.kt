package app.mobius.domain

import platform.Foundation.NSUUID

data class Person(
    val personUUID: NSUUID,
    val username: String
)