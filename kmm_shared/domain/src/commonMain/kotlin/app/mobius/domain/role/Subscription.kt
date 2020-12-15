package app.mobius.domain.role

import com.benasher44.uuid.Uuid

class Subscription(
    val uuid: Uuid?,
    val subscriptionUUID: Uuid? = null,
    val subscriptionStatus: SubscriptionStatus = SubscriptionStatus.FREE
)


enum class SubscriptionStatus {
    FREE, PREMIUM
}