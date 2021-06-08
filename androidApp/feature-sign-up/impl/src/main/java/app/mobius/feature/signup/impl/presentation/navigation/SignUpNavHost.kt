package app.mobius.feature.signup.impl.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.mobius.feature.signup.impl.presentation.ui.screen.*

@Composable
fun SignUpNavHost() {
    val navHostController = signUpNavHostController()

    NavHost(
        navController = navHostController,
        startDestination = SignUpDestinations.FULLNAME_SCREEN
    ) {
        composable(route = SignUpDestinations.FULLNAME_SCREEN) { FullnameScreen() }
        composable(route = SignUpDestinations.BIRHDATE_SCREEN) { BirthDateScreen() }
        composable(route = SignUpDestinations.GENDERS_SCREEN) { GenderScreen() }
        composable(route = SignUpDestinations.PHONES_SCREEN) { PhoneScreen() }
        composable(route = SignUpDestinations.PASSWORDS_SCREEN) { PasswordScreen() }
        composable(route = SignUpDestinations.LEGALS_SCREEN) { LegalScreen() }
    }
}