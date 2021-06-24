package app.mobius.feature.signup.wiring.presentation

import androidx.compose.ui.ExperimentalComposeUiApi
import app.mobius.feature.signup.impl.presentation.navigation.ToSignUpNavigation
import app.mobius.feature.signup.open.presentation.ui.SignUpNavigation

@ExperimentalComposeUiApi
val signUpNavigation: SignUpNavigation = ToSignUpNavigation()