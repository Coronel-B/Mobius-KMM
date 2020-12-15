package app.mobius.domain.setting

import com.benasher44.uuid.Uuid

class LinkedAccount(
        val uuid: Uuid?,
        val facebook: Facebook? = null
)

class Facebook(
        val uuid: Uuid?,
        val username: String,
        val facebookId: Long? = null
)