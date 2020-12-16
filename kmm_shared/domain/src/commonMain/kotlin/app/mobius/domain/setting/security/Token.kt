package app.mobius.domain.setting.security

import app.mobius.domain.utils.currentLocalDate
import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDate

data class Token(
    val uuid: Uuid? = null,
    val token: String? = null,
    val created: LocalDate = currentLocalDate(),
    val expiry: LocalDate
) {
    constructor() : this(created = currentLocalDate(), expiry = currentLocalDate())
}