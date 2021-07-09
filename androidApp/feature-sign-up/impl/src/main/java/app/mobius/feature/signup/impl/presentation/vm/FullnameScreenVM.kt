package app.mobius.feature.signup.impl.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import org.itdevexpert.viewmodel.flow.combineState
import org.itdevexpert.viewmodel.flow.combineStateFlow

@ExperimentalCoroutinesApi
class FullnameScreenVM : ViewModel() {

    private val _name = MutableStateFlow("")
    private val _isNameError = MutableStateFlow(false)
    private val _surname = MutableStateFlow("")
    private val _isSurnameError = MutableStateFlow(false)

    private val _isStateInitial = combineState(
        flow1 = _name,
        flow2 = _surname,
        scope = viewModelScope
    ) { name, surname ->
        name == "" || surname == ""
    }

    private val _existsFieldError = combineState(
        flow1 = _isNameError,
        flow2 = _isSurnameError,
        scope = viewModelScope
    ) { nameError, surnameError ->
        Log.d(this::class.java.simpleName, "$nameError $surnameError")
        nameError || surnameError
    }

    private val _isValidForm2 = combineStateFlow(
        flows = arrayOf(_isStateInitial, _existsFieldError),
        scope = viewModelScope
    ) { combinedFlows: Array<Any> ->
        combinedFlows.map {

        }
//        !isStateInitial && !existsFieldError

    }

    data class A(val a: String)
    data class B(val b: Int)

    private val test1 = MutableStateFlow(A("a"))
    private val test2 = MutableStateFlow(B(2))

    @Suppress("CHANGING_ARGUMENTS_EXECUTION_ORDER_FOR_NAMED_VARARGS")
    private val _isValidForm = combineStateFlow(
        flows = arrayOf(test1, test2),
        scope = viewModelScope
    ) { combinedFlows: Array<Any> ->
        combinedFlows.map {
            val doSomething = when (it) {
                is A -> true
                is B -> false
                else -> false
            }
        }
    }

    val name: StateFlow<String> = _name
    val isNameError: StateFlow<Boolean> = _isNameError
    val surname: StateFlow<String> = _surname
    val isSurnameError: StateFlow<Boolean> = _isSurnameError
    val isValidForm: StateFlow<Boolean> = _isValidForm2

    fun onNameChange(newName: String) {
        _name.value = newName
        _isNameError.value = newName.isEmpty() || !withoutNumbers(newName)
        test1.value = A(newName)
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