package app.mobius.domain.role

import com.benasher44.uuid.Uuid

/**
 * https://stackoverflow.com/a/14286082/5279996
 */
data class Permission(
    val uuid: Uuid? = null,
    val operation: Operation?,
    var resource: Resource?
)