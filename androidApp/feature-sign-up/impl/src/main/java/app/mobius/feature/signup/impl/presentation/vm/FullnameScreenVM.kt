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
    private val _surname = MutableLiveData("")
    private val _isValidForm = MediatorLiveData(false)

    val name: LiveData<String> = _name
    val surname: LiveData<String> = _surname
    val isValidForm: LiveData<Boolean> = _isValidForm

    fun onNameChange(newName: String) {
        if (newName.isNotEmpty()) _isValidForm
        _name.value = newName
    }

    fun onSurnameChange(newSurname: String) {
        _surname.value = newSurname
    }

}