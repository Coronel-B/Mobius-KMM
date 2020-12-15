package app.mobius.domain.utils

import kotlinx.datetime.Clock
import org.junit.Test

class KotlinXDateTimeTest {

    @Test
    fun `dependencie_kotlinx_datetime`() {
        print(Clock.System.now())
    }

}