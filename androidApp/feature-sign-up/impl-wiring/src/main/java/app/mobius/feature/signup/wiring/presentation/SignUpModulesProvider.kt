package app.mobius.feature.signup.wiring.presentation

import androidx.compose.ui.ExperimentalComposeUiApi
import app.mobius.feature.signup.open.presentation.ui.SignUpNavigation

object SignUpModulesProvider {

    @ExperimentalComposeUiApi
    fun bindSignUpNavigation() : SignUpNavigation = signUpNavigation

}