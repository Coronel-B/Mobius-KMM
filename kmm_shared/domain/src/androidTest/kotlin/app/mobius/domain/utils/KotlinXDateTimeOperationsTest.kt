package app.mobius.domain.utils

import kotlinx.datetime.*
import org.junit.Test

class KotlinXDateTimeOperationsTest {

    @Test
    fun dependencie_kotlinx_datetime() {
//        Getting the current moment of time
        val currentMoment = Clock.System.now()
        println(currentMoment)

//        Converting an instant to local date and time components
        val datetimeInGMT3 = currentMoment.toLocalDateTime(TimeZone.of("GMT-3"))
        println(datetimeInGMT3)

        println(currentLocalDate())
    }

}

