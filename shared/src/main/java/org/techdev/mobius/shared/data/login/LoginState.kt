package org.techdev.mobius.shared.data.login

/**
 * PRO: Describe models that represent the status of the Login screen
 * PRE: The way you use the model depends entirely on the view.
 * OBS: Los estados específicos de pantallas pueden tener cualquier estructura necesaria.
 * Para el estado de inicio de sesión un enumerado es suficiente.
 */
enum class LoginState {
    Success, WrongUserName, WrongPassword
}