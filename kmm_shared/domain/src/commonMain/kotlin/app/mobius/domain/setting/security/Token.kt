package app.mobius.domain.setting.security

import com.benasher44.uuid.Uuid

data class Token(
    val uuid: Uuid?,
    val token: String? = null,
    val created: Date = Date(),
    val expiry: Date
)