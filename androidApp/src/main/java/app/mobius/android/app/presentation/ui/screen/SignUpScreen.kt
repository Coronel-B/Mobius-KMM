package app.mobius.android.app.presentation.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import app.mobius.feature.signup.open.presentation.ui.SignUpNavigation
import app.mobius.feature.signup.wiring.presentation.SignUpModulesProvider

@ExperimentalUnitApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SignUpScreen() {
    val signUpNavigation: SignUpNavigation = SignUpModulesProvider.bindSignUpNavigation()
    return signUpNavigation.CreateYourIdentityComposable()   //Has a @Composable
}