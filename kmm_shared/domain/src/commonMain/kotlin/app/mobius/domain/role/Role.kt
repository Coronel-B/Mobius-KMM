package app.mobius.domain.role

import app.mobius.domain.LivenessStatus
import com.benasher44.uuid.Uuid

data class Role(
    val uuid: Uuid? = null,
    val livenessStatus: LivenessStatus = LivenessStatus.UNSOLICITED,
    val securityLevel: Byte = 0,
    val subscription: Subscription,
    val permissions: List<Permission>

)