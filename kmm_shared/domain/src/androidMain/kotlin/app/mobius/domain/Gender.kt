package app.mobius.domain

import java.util.*

data class Gender(
        var genderUUID: UUID? = null,
        var type: String,
        var description: String? = null,
        var iconUrl: String? = null
) {
    constructor() : this(genderUUID = null, type = "")
}