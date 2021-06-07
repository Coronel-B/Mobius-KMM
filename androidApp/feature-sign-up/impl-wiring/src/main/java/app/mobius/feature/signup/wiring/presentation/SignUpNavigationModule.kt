package app.mobius.feature.signup.wiring.presentation

import app.mobius.feature.signup.impl.presentation.navigation.ToSignUpNavigation
import app.mobius.feature.signup.open.presentation.ui.SignUpNavigation

val signUpNavigation: SignUpNavigation = ToSignUpNavigation()