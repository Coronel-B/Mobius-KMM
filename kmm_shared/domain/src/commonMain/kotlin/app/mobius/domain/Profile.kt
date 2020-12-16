package app.mobius.domain

import app.mobius.domain.utils.currentLocalDate
import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDate

data class Profile(
    val uuid: Uuid? = null,
    val name: String,
    val surname: String,
    val location: Location,
    val nationality: Country,
    val avatarUrl: String?,
    val nickname: String?,
    val biography: String?,
    val phone: Phone,
    val birthdate: LocalDate,
    val sex: Sex,
    val gender: Gender? = null
){
    constructor() : this(
        name = "",
        surname = "",
        location = Location(),
        nationality = Country(),
        avatarUrl = null,
        nickname = null,
        biography = null,
        phone = Phone(),
        birthdate = currentLocalDate(),
        sex = Sex.F,
    )
}

data class Phone(
    val uuid: Uuid? = null,
    val codeCountry: String,
    val codeArea: String,
    val number: Long
) {
    constructor() : this(codeCountry = "", codeArea = "", number = -1)
}

enum class Sex {
    F, M
}

