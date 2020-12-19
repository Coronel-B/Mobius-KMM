package app.mobius.feature_sign_up.domain.dto

data class PersonSignUpDto(
    val username: String,
    val profile: ProfileSignUpDto,
    val setting: SettingSignUpDto,
)