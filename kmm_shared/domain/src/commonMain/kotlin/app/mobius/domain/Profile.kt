package app.mobius.domain

import com.benasher44.uuid.Uuid

// TODO: https://github.com/Kotlin/kotlinx-datetime
expect class Profile(
    uuid: Uuid?,
    name: String,
    surname: String,
    location: Location,
    nationality: Country,
    avatarUrl: String?,
    nickname: String?,
    biography: String?,
    phone: Phone,
    birthdate: Date,
    sex: Sex,
    gender: Gender? = null
) {

}

expect class Phone(
    uuid: Uuid?,
    codeCountry: String,
    codeArea: String,
    number: Long
) {

}

enum class Sex {
    F, M
}

