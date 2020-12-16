package app.mobius.domain

import app.mobius.domain.role.Role
import app.mobius.domain.setting.Setting
import com.benasher44.uuid.Uuid

/**
 * Be endowed with reason, self-aware and possessed of their own person
 */
data class Person(
    val uuid: Uuid? = null,
    val username: String,
    val profile: Profile?,
    val setting: Setting,
    val role: Role,
) {
    constructor() : this(username = "", profile = Profile(), setting = Setting(), role = Role())
}

