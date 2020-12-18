package app.mobius.feature_sign_up.domain.dto.security

/**
 * @param securityLevel: [0,4]
 */
data class SecuritySignUpDto(
    val authentication: AuthSignUpDto,
    val securityLevel: Byte = 0,
    val securityMethods: SecurityMethodsSignUpDto? = null,
)