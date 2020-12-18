package app.mobius.feature_sign_up.domain.dto.security

import app.mobius.domain.setting.security.EmailVerificationStatus
import app.mobius.domain.setting.security.IdentityVerification
import app.mobius.domain.setting.security.Token
import com.benasher44.uuid.Uuid

/**
 *  Obs: Are manipulated by the person
 */
data class SecurityMethodsSignUpDto(
    val uuid: Uuid? = null,
    val identityVerification: IdentityVerification? = null,
    val twoFactorAuth: TwoFactorAuthSignUpDto? = null,
    val antiPishingCode: AntiPishingCodeSignUpDto? = null,
)

data class TwoFactorAuthSignUpDto(
    val uuid: Uuid? = null,
    val googleAuthentication: GoogleAuthSignUpDto,
    val smsAuthentication: SMSAuthenticationSignUpDto,
    val emailVerification: EmailVerificationSignUpDto
)

data class GoogleAuthSignUpDto(
    val uuid: Uuid? = null,
    val enable: Boolean = false,
    val verificationCode: Int
)

data class SMSAuthenticationSignUpDto(
    val uuid: Uuid? = null,
    val enable: Boolean = false,
    val verificationCode: Int
)

data class EmailVerificationSignUpDto(
    val uuid: Uuid? = null,
    val enable: Boolean = false,
    val emailVerificationStatus: EmailVerificationStatus = EmailVerificationStatus.UNCONFIRMED,
    val token: Token? = null
)

data class AntiPishingCodeSignUpDto(
    val uuid: Uuid? = null,
    val enable: Boolean = false,
    val code: String
)