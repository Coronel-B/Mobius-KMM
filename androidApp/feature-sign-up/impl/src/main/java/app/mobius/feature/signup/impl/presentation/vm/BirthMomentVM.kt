package app.mobius.feature.signup.impl.presentation.vm

import androidx.lifecycle.ViewModel
import app.mobius.domain.utils.currentLocalDate
import app.mobius.domain.utils.currentLocalTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate
import java.time.LocalTime
import java.time.LocalDate as LocalDateJava

@ExperimentalCoroutinesApi
class BirthMomentVM : ViewModel() {

    private val _date = MutableStateFlow(currentLocalDate())
    private val _time = MutableStateFlow(currentLocalTime())

    val date: StateFlow<LocalDate> = _date
    val time: StateFlow<String> = _time

    fun onDateChange(localDateJava: LocalDateJava) {
        _date.value = LocalDate.parse(localDateJava.toString())
    }

    fun onTimeChange(localTime: LocalTime) {
        _time.value = localTime.toString()
    }

}