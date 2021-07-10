@file:Suppress("CHANGING_ARGUMENTS_EXECUTION_ORDER_FOR_NAMED_VARARGS")

package app.mobius.feature.signup.impl.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mobius.domain.utils.currentLocalDate
import app.mobius.domain.utils.currentLocalTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import org.itdevexpert.viewmodel.flow.combineState
import org.itdevexpert.viewmodel.flow.combineStateFlow
import java.time.LocalTime
import java.time.LocalDate as LocalDateJava

@ExperimentalCoroutinesApi
class BirthMomentVM : ViewModel() {

    private val _date = MutableStateFlow(currentLocalDate())
    private val _time = MutableStateFlow(currentLocalTime())
    private val _isStateInitialOfDate = combineState(
        flow1 = _date,
        scope = viewModelScope
    ) {
        it == currentLocalDate()
    }
    private val _isDateError = MutableStateFlow(false)
    private val _isValidData = combineStateFlow(
        flows = arrayOf(_isDateError, _isStateInitialOfDate),
        scope = viewModelScope
    ) {
        it.all { e ->
            !e
        }
    }

    val isDateError: StateFlow<Boolean> = _isDateError
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