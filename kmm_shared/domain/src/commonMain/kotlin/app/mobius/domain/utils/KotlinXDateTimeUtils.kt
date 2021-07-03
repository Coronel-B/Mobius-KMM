@file:kotlin.jvm.JvmMultifileClass
@file:kotlin.jvm.JvmName("KotlinXDateTimeUtilsCommon")

package app.mobius.domain.utils

import kotlinx.datetime.*

const val ZONE_ID_BUENOS_AIRES = "GMT-3"

fun Instant.toLocalDate(timeZone: TimeZone): LocalDate = toLocalDateTime(timeZone).date

fun currentLocalDate(): LocalDate {
    val currentMoment = Clock.System.now()
    return currentMoment.toLocalDate(TimeZone.of(ZONE_ID_BUENOS_AIRES))
}

fun currentLocalDateTime(): LocalDateTime {
    val currentMoment = Clock.System.now()
    return currentMoment.toLocalDateTime(TimeZone.of(ZONE_ID_BUENOS_AIRES))
}

fun currentLocalTime(): String {
    val currentLocalDateTime = currentLocalDateTime()
    return "${currentLocalDateTime.hour}:${currentLocalDateTime.minute}"
}
