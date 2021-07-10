package app.mobius.feature.signup.wiring.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import app.mobius.feature.signup.open.presentation.ui.SignUpNavigation

object SignUpModulesProvider {

    @ExperimentalAnimationApi
    @ExperimentalUnitApi
    @ExperimentalComposeUiApi
    fun bindSignUpNavigation() : SignUpNavigation = signUpNavigation

}