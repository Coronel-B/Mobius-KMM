package app.mobius.domain.setting.security

import app.mobius.domain.utils.currentLocalDateTime
import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDateTime

data class Token(
    val uuid: Uuid? = null,
    val token: String? = null,
    val created: LocalDateTime = currentLocalDateTime(),
    val expiry: LocalDateTime
)