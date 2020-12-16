package app.mobius.domain.setting.security

import com.benasher44.uuid.Uuid

/**
 * @param securityLevel: [0,4]
 */
data class Security(
    val uuid: Uuid? = null,
    val authentication: Authentication,
    val securityLevel: Byte = 0,
    val securityMethods: SecurityMethods? = null,
)