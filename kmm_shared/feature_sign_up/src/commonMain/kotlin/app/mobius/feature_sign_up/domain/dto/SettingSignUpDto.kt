package app.mobius.feature_sign_up.domain.dto

import app.mobius.domain.setting.Theme
import app.mobius.domain.setting.security.Security

data class SettingSignUpDto(
    val account: AccountSignUpDto,
    val security: Security,
    val theme: Theme = Theme.DEFAULT
)

data class AccountSignUpDto(
    val linkedAccount: LinkedAccountSignUpDto? = null,
)

data class LinkedAccountSignUpDto(
    val facebook: FacebookSignUpDto? = null
)

data class FacebookSignUpDto(
    val username: String,
    val facebookId: Long? = null
)