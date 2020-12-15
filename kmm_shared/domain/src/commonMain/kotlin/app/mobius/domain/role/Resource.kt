package app.mobius.domain.role

import com.benasher44.uuid.Uuid

class Resource(
    val uuid: Uuid?,
    val name: String,
    val location: String,
)