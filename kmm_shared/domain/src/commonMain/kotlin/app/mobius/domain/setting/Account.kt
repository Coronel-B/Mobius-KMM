package app.mobius.domain.setting

import com.benasher44.uuid.Uuid

data class Account(
    val uuid: Uuid?,
    val linkedAccount: LinkedAccount? = null,
)