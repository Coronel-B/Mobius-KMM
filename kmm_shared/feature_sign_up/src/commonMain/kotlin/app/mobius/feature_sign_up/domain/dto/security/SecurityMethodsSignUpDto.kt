package app.mobius.feature_sign_up.domain.dto.security

import app.mobius.domain.setting.security.VerificationStatus

/**
 *  Obs: Are manipulated by the person
 */
data class SecurityMethodsSignUpDto(
    val twoFactorAuth: TwoFactorAuthSignUpDto? = null,
)

data class TwoFactorAuthSignUpDto(
    val smsAuthentication: SMSAuthenticationSignUpDto,
)

//TODO: Not necessary (?
data class SMSAuthenticationSignUpDto(
    val enable: Boolean = false,
    val verificationStatus: VerificationStatus = VerificationStatus.UNCONFIRMED,
    val verificationCode: Int
)