package app.mobius.feature.signup.impl.presentation.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * https://developer.android.com/jetpack/compose/state?hl=es-419
 */
class FullnameScreenVM : ViewModel() {

    private val _name = MutableStateFlow("")
    private val _isNameError = MutableStateFlow(false)
    private val _surname = MutableStateFlow("")
    private val _isSurnameError = MutableStateFlow(false)
    private val _isValidForm = MutableStateFlow(false)

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