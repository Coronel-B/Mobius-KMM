package app.mobius.feature_sign_up.domain.dto.security

import com.benasher44.uuid.Uuid

/**
 * Represents a authentication credential
 * TODO: Add app token
 */
data class AuthSignUpDto(
    val uuid: Uuid? = null,
    val session: SessionSignUpDto,
    val basicAuth: BasicAuthSignUpDto
)

/**
 * Represents a traditional or basic authentication
 */
data class BasicAuthSignUpDto(
    val email: String,
    val password: PasswordSignUpDto,
)

data class PasswordSignUpDto(
    val uuid: Uuid? = null,
    val apiHashPassword: String,
    val dbHashPassword: String? = null,
)