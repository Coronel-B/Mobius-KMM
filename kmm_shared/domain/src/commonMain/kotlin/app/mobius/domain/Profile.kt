package app.mobius.domain

import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDate

class Profile(
    uuid: Uuid?,
    name: String,
    surname: String,
    location: Location,
    nationality: Country,
    avatarUrl: String?,
    nickname: String?,
    biography: String?,
    phone: Phone,
    birthdate: LocalDate,
    sex: Sex,
    gender: Gender? = null
) {

}

class Phone(
    uuid: Uuid?,
    codeCountry: String,
    codeArea: String,
    number: Long
) {

}

enum class Sex {
    F, M
}

