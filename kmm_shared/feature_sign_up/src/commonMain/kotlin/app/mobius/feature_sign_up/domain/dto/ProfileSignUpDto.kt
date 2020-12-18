package app.mobius.feature_sign_up.domain.dto

import app.mobius.domain.Location
import app.mobius.domain.Sex
import com.benasher44.uuid.Uuid

data class ProfileSignUpDto(
    val avatarUrl: String?,
    val birthDate: String,
    val birthTime: String,
    val biography: String?,
    val name: String,
    val nickname: String?,
    val surname: String,
    val gender: GenderSignUpDto? = null,
    val location: Location,
    val nationality: CountrySignUpDto,
    val phone: PhoneSignUpDto,
    val sex: Sex
)



data class GenderSignUpDto(
    val uuid: Uuid? = null,
    var type: String,
)

data class PhoneSignUpDto(
    val codeCountry: String,
    val codeArea: String,
    val number: Long
)

data class CountrySignUpDto(
    val uuid: Uuid? = null,
    val name: String,
)
