package app.mobius.domain.setting.security

import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDate

/**
 * Represents a authentication credential
 * TODO: Add app token
 */
data class Authentication(
    val uuid: Uuid? = null,
    val session: Session,
    val basicAuth: BasicAuth
)

/**
 * Represents a traditional or basic authentication
 * OBS: Other methods will not be considered
 */
data class BasicAuth(
    val uuid: Uuid? = null,
    val email: String,
    val password: Password,
)

data class Password(
    val uuid: Uuid? = null,
    val apiHashPassword: String,
    val dbHashPassword: String? = null,
    val resetPasswordToken: String? = null,
    val resetPasswordTokenExpire: LocalDate? = null,
)