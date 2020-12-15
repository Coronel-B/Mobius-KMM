package app.mobius.domain

import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDate

data class Profile(
    val uuid: Uuid?,
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
)

data class Phone(
    val uuid: Uuid?,
    val codeCountry: String,
    val codeArea: String,
    val number: Long
)

enum class Sex {
    F, M
}

