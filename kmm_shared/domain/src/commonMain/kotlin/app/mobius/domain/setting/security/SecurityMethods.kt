package app.mobius.domain.setting.security

import com.benasher44.uuid.Uuid

/**
 *  Obs: Are manipulated by the person
 */
class SecurityMethods(
    val uuid: Uuid?,
    val identityVerification: IdentityVerification? = null,
    val twoFactorAuth: TwoFactorAuth? = null,
    val antiPishingCode: AntiPishingCode? = null,
)

class TwoFactorAuth(
    val uuid: Uuid?,
    val googleAuthentication: GoogleAuth,
    val smsAuthentication: SMSAuthentication,
    val emailVerification: EmailVerification
)

class GoogleAuth(
    val uuid: Uuid?,
    val enable: Boolean = false,
    val verificationCode: Int
)

class SMSAuthentication(
    val uuid: Uuid?,
    val enable: Boolean = false,
    val verificationCode: Int
)

class EmailVerification(
    val uuid: Uuid?,
    val enable: Boolean = false,
    val emailVerificationStatus: EmailVerificationStatus = EmailVerificationStatus.UNCONFIRMED,
    val token: Token? = null
)

enum class EmailVerificationStatus {
    UNCONFIRMED, INVALID, PENDING, CONFIRMED
}

class AntiPishingCode(
    val uuid: Uuid?,
    val enable: Boolean = false,
    val code: String
)