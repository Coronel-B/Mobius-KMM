package app.mobius.domain

import com.benasher44.uuid.Uuid

class Gender(
    val uuid: Uuid?,
    var type: String,
    var description: String? = null,
    var iconUrl: String? = null
)