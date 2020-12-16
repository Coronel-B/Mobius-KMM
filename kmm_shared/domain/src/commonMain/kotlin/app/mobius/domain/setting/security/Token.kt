package app.mobius.domain.setting.security

import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDate

data class Token(
    val uuid: Uuid? = null,
    val token: String? = null,
    val created: LocalDate, //TODO: Set now() as default
    val expiry: LocalDate
)