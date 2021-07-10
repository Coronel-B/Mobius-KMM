package app.mobius.feature.signup.impl.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import app.mobius.feature.signup.impl.presentation.ui.SignUpScreen
import app.mobius.feature.signup.open.presentation.ui.SignUpNavigation
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
class ToSignUpNavigation : SignUpNavigation {


    @Composable
    override fun CreateYourIdentityComposable() {
        SignUpScreen()
    }

}