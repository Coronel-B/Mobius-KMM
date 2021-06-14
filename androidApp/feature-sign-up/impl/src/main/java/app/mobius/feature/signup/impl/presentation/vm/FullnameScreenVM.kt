package app.mobius.feature.signup.impl.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * https://developer.android.com/jetpack/compose/state?hl=es-419
 */
class FullnameScreenVM : ViewModel() {

    private val _name = MutableLiveData("")
    private val _isNameError = MutableLiveData(false)
    private val _surname = MutableLiveData("")
    private val _isSurnameError = MutableLiveData(false)
    private val _isValidForm = MediatorLiveData<Boolean>().apply {
        value = false
    }

    val name: LiveData<String> = _name
    val isNameError: LiveData<Boolean> = _isNameError
    val surname: LiveData<String> = _surname
    val isSurnameError: LiveData<Boolean> = _isSurnameError
    val isValidForm: LiveData<Boolean> = _isValidForm

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