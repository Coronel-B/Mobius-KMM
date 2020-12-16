package app.mobius.domain.setting.security

import com.benasher44.uuid.Uuid

/**
 * TODO: Check whether to rename some type of authorization
 */
data class Session(
    val uuid: Uuid? = null,
    val accessToken: AccessToken,
    val dailyReloadToken: DailyReloadToken,
    val monthlyReloadToken: MonthlyReloadToken,
) {
    constructor() : this(accessToken = AccessToken(), dailyReloadToken = DailyReloadToken(), monthlyReloadToken = MonthlyReloadToken())
}

/**
 * Represents the dataAccess token or AT
 * Expires in 5 minutes
 * OBS: Dont use AccessToken as name to avoid conflicts of mapping TODO
 * @param token: Access token or API key to identify the person
 */
data class AccessToken(
    val uuid: Uuid? = null,
    val token: Token
) {
    constructor() : this(token = Token())
}

/**
 * Represents a daily token to reload the AT
 * @param keepSessionDaily: Expires in 1 day but is optional for the person
 */
data class DailyReloadToken(
    val uuid: Uuid? = null,
    val keepSessionDaily: Boolean = false,
    val token: Token
) {
    constructor() : this(token = Token())
}

/**
 * Represents a monthly token to reload the dailyReloadToken
 * Obs: Expires in 1 month
 */
data class MonthlyReloadToken(
    val uuid: Uuid? = null,
    val token: Token
) {
    constructor() : this(token = Token())
}