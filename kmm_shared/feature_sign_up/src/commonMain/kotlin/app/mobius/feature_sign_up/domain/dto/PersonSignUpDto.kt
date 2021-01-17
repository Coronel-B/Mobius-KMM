package app.mobius.feature_sign_up.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class PersonSignUpDto(
    val username: String,
//    val profile: ProfileSignUpDto,
//    val setting: SettingSignUpDto,
)