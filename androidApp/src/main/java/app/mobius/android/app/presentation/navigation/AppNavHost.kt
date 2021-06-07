package app.mobius.android.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.mobius.android.app.presentation.navigation.AppDestinations.LOGIN_SCREEN
import app.mobius.android.app.presentation.navigation.AppDestinations.SIGN_UP_SCREEN
import app.mobius.android.app.presentation.navigation.AppDestinations.WELCOME_SCREEN
import app.mobius.android.app.presentation.navigation.screen.LoginScreen
import app.mobius.android.app.presentation.navigation.screen.SignUpScreen
import app.mobius.android.app.presentation.navigation.screen.WelcomeScreen

@Composable
fun ComposeNavigation() {
    val navHostController = appNavHostController()

    NavHost(
        navController = navHostController,
        startDestination = WELCOME_SCREEN
    ) {
        composable(route = WELCOME_SCREEN) { WelcomeScreen(navHostController) }
        composable(route = SIGN_UP_SCREEN) { SignUpScreen() }
        composable(route = LOGIN_SCREEN) { LoginScreen() }
    }

}