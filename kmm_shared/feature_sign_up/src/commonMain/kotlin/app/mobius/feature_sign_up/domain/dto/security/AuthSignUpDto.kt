package app.mobius.feature_sign_up.domain.dto.security

import app.mobius.domain.setting.security.Session
import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDate

/**
 * Represents a authentication credential
 * TODO: Add app token
 */
data class AuthSignUpDto(
    val uuid: Uuid? = null,
    val session: Session,
    val basicAuth: BasicAuthSignUpDto
)

/**
 * Represents a traditional or basic authentication
 * OBS: Other methods will not be considered
 */
data class BasicAuthSignUpDto(
    val uuid: Uuid? = null,
    val email: String,
    val password: PasswordSignUpDto,
) {
    constructor() : this(email = "", password = PasswordSignUpDto())
}

data class PasswordSignUpDto(
    val uuid: Uuid? = null,
    val apiHashPassword: String,
    val dbHashPassword: String? = null,
    val resetPasswordToken: String? = null,
    val resetPasswordTokenExpire: LocalDate? = null,
) {
    constructor() : this(apiHashPassword = "-1")
}
