package app.mobius.feature.signup.impl.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import org.itdevexpert.viewmodel.flow.combineStateFlow

@Suppress("CHANGING_ARGUMENTS_EXECUTION_ORDER_FOR_NAMED_VARARGS")
@ExperimentalCoroutinesApi
class FullnameScreenVM : ViewModel() {

    private val _name = MutableStateFlow("")
    private val _isNameError = MutableStateFlow(false)
    private val _surname = MutableStateFlow("")
    private val _isSurnameError = MutableStateFlow(false)

    private val _isStateInitial = combineStateFlow(
        flows = arrayOf(_name, _surname),
        scope = viewModelScope
    ) {
        it.any { field ->
            field == ""
        }
    }

    private val _existsFieldError = combineStateFlow(
        flows = arrayOf(_isNameError, _isSurnameError),
        scope = viewModelScope
    ) { combinedFlows ->
        combinedFlows.any {
            it
        }
    }

    private val _isValidForm = combineStateFlow(
        flows = arrayOf(_isStateInitial, _existsFieldError),
        scope = viewModelScope
    ) { combinedFlows: Array<Any> ->
        combinedFlows.all {
            !(it as Boolean)
        }
    }

    val name: StateFlow<String> = _name
    val isNameError: StateFlow<Boolean> = _isNameError
    val surname: StateFlow<String> = _surname
    val isSurnameError: StateFlow<Boolean> = _isSurnameError
    val isValidForm: StateFlow<Boolean> = _isValidForm

    fun onNameChange(newName: String) {
        _name.value = newName
        _isNameError.value = newName.isEmpty() || !withoutNumbers(newName)
    }

    fun onSurnameChange(newSurname: String) {
        _surname.value = newSurname
        _isSurnameError.value = newSurname.isEmpty() || !withoutNumbers(newSurname)
    }

    /**
     * Check that any number not exists in text
     * Source: https://stackoverflow.com/a/4440309/5279996
     */
    private fun withoutNumbers(text: String) = text.contains(regex = Regex("^[^0-9]+$"))

}