package app.mobius.feature.signup.impl.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.mobius.feature.signup.impl.presentation.ui.screen.*

@Composable
fun SignUpNavHost() {
    val signUpNavHostController = signUpNavHostController()
    val signUpActions = actions(signUpNavHostController = signUpNavHostController)

    NavHost(
        navController = signUpNavHostController,
        startDestination = SignUpDestinations.FULLNAME_SCREEN
    ) {
        composable(route = SignUpDestinations.FULLNAME_SCREEN) {
            FullnameScreen(signUpActions.birthdateScreen)
        }
        composable(route = SignUpDestinations.BIRHDATE_SCREEN) { BirthDateScreen() }
        composable(route = SignUpDestinations.GENDER_SCREEN) { GenderScreen() }
        composable(route = SignUpDestinations.PHONE_SCREEN) { PhoneScreen() }
        composable(route = SignUpDestinations.PASSWORD_SCREEN) { PasswordScreen() }
        composable(route = SignUpDestinations.LEGALS_SCREEN) { LegalScreen() }
    }
}