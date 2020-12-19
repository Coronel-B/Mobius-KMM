package app.mobius.feature_sign_up.domain.dto.security

import app.mobius.domain.utils.currentLocalDateTime
import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDateTime

/**
 * TODO: Check whether to rename some type of authorization
 */
data class SessionSignUpDto(
    val uuid: Uuid? = null,
    val accessToken: AccessTokenSignUpDto,
    val dailyReloadToken: DailyReloadTokenSignUpDto,
    val monthlyReloadToken: MonthlyReloadTokenSignUpDto,
)
/**
 * Represents the dataAccess token or AT
 * Expires in 5 minutes
 * @param token: Access token or API key to identify the person
 */
data class AccessTokenSignUpDto(
    val uuid: Uuid? = null,
    val token: TokenSignUpDto
)

/**
 * Represents a daily token to reload the AT
 * @param keepSessionDaily: Expires in 1 day but is optional for the person
 */
data class DailyReloadTokenSignUpDto(
    val uuid: Uuid? = null,
    val keepSessionDaily: Boolean = false,
    val token: TokenSignUpDto
)

/**
 * Represents a monthly token to reload the dailyReloadToken
 * Obs: Expires in 1 month
 */
data class MonthlyReloadTokenSignUpDto(
    val uuid: Uuid? = null,
    val token: TokenSignUpDto
)

data class TokenSignUpDto(
    val uuid: Uuid? = null,
    val token: String? = null,
    val created: LocalDateTime = currentLocalDateTime(),
    val expiry: LocalDateTime
) {
    constructor() : this(created = currentLocalDateTime(), expiry = currentLocalDateTime())
}