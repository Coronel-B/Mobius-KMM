package app.mobius.domain.utils

import kotlinx.datetime.*

//  TODO: Delete if PR merge ends: https://github.com/Kotlin/kotlinx-datetime/pull/86
actual fun Instant.toLocalDate(timeZone: TimeZone): LocalDate = try {
    val localDateTime = this.toLocalDateTime(timeZone)
    LocalDate(localDateTime.year, localDateTime.monthNumber, localDateTime.dayOfMonth)
} catch (e: DateTimeArithmeticException) {
    throw DateTimeArithmeticException(e)
}