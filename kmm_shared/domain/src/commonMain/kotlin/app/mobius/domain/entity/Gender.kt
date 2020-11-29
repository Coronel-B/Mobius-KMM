package app.mobius.domain.entity

data class Gender(
        var genderUUID: String? = null, //TODO: Use UUID
        var type: String,
        var description: String? = null,
        var iconUrl: String? = null
) {
    constructor() : this(genderUUID = null, type = "")
}