package app.mobius.credential_managment

import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDateTime

data class AppGlobalSessionToken(
    val uuid: Uuid,
    val token: String,
    val expiry: LocalDateTime
)