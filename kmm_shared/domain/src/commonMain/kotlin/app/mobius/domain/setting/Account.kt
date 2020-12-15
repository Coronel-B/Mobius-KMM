package app.mobius.domain.setting

import com.benasher44.uuid.Uuid

class Account(
    val uuid: Uuid?,
    val linkedAccount: LinkedAccount? = null,
)