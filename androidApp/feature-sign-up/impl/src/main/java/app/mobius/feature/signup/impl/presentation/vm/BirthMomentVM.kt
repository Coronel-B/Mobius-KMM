package app.mobius.feature.signup.impl.presentation.vm

import androidx.lifecycle.ViewModel
import app.mobius.domain.utils.currentLocalDate
import app.mobius.domain.utils.currentLocalTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import org.itdevexpert.viewmodel.flow.combineState
import java.time.LocalTime
import java.time.LocalDate as LocalDateJava

@ExperimentalCoroutinesApi
class BirthMomentVM : ViewModel() {

    private val _date = MutableStateFlow(currentLocalDate())
    private val _time = MutableStateFlow(currentLocalTime())

    private val _isDateError = MutableStateFlow(true)
    private val _isValidData = combineState(_isDateError) {
        !it
    }

    val isValidData: StateFlow<Boolean> = _isValidData
    val date: StateFlow<LocalDate> = _date
    val time: StateFlow<String> = _time

    fun onDateChange(localDateJava: LocalDateJava) {
        _date.value = LocalDate.parse(localDateJava.toString())
        _isDateError.value = (currentLocalDate().year - _date.value.year) <= 13
    }

    fun onTimeChange(localTime: LocalTime) {
        _time.value = localTime.toString()
    }

}