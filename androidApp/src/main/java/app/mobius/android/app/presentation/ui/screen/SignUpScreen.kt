package app.mobius.android.app.presentation.ui.screen

import androidx.compose.runtime.Composable
import app.mobius.feature.signup.open.presentation.ui.SignUpNavigation
import app.mobius.feature.signup.wiring.presentation.SignUpModulesProvider

@Composable
fun SignUpScreen() {
    val signUpNavigation: SignUpNavigation = SignUpModulesProvider.bindSignUpNavigation()
    return signUpNavigation.CreateYourIdentityComposable()   //Has a @Composable
}