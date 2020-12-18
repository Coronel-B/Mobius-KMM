package app.mobius.feature_sign_up.domain.dto.security

data class SecuritySignUpDto(
    val authentication: AuthSignUpDto,
    val securityMethods: SecurityMethodsSignUpDto? = null,
)