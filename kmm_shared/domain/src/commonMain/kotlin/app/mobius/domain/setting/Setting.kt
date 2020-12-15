package app.mobius.domain.setting

import app.mobius.domain.setting.security.Security
import com.benasher44.uuid.Uuid

data class Setting(
    val uuid: Uuid?,
    val account: Account,
    val security: Security,
    val theme: Theme = Theme.DEFAULT
)

enum class Theme {
    LIGHT, DARK, DEFAULT
}