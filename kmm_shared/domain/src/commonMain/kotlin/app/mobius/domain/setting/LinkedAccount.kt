package app.mobius.domain.setting

import com.benasher44.uuid.Uuid

data class LinkedAccount(
        val uuid: Uuid?,
        val facebook: Facebook? = null
)

data class Facebook(
        val uuid: Uuid?,
        val username: String,
        val facebookId: Long? = null
)