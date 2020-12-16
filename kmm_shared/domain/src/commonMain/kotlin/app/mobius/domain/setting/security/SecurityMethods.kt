package app.mobius.domain.setting.security

import com.benasher44.uuid.Uuid

/**
 *  Obs: Are manipulated by the person
 */
data class SecurityMethods(
    val uuid: Uuid? = null,
    val identityVerification: IdentityVerification? = null,
    val twoFactorAuth: TwoFactorAuth? = null,
    val antiPishingCode: AntiPishingCode? = null,
)

data class TwoFactorAuth(
    val uuid: Uuid? = null,
    val googleAuthentication: GoogleAuth,
    val smsAuthentication: SMSAuthentication,
    val emailVerification: EmailVerification
)

data class GoogleAuth(
    val uuid: Uuid? = null,
    val enable: Boolean = false,
    val verificationCode: Int
)

data class SMSAuthentication(
    val uuid: Uuid? = null,
    val enable: Boolean = false,
    val verificationCode: Int
)

data class EmailVerification(
    val uuid: Uuid? = null,
    val enable: Boolean = false,
    val emailVerificationStatus: EmailVerificationStatus = EmailVerificationStatus.UNCONFIRMED,
    val token: Token? = null
)

enum class EmailVerificationStatus {
    UNCONFIRMED, INVALID, PENDING, CONFIRMED
}

data class AntiPishingCode(
    val uuid: Uuid? = null,
    val enable: Boolean = false,
    val code: String
)