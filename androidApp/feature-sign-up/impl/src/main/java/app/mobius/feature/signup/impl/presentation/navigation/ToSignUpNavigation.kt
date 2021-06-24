package app.mobius.feature.signup.impl.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import app.mobius.feature.signup.impl.presentation.ui.SignUpScreen
import app.mobius.feature.signup.open.presentation.ui.SignUpNavigation

@ExperimentalComposeUiApi
class ToSignUpNavigation : SignUpNavigation {

    @Composable
    override fun CreateYourIdentityComposable() {
        SignUpScreen()
    }

}