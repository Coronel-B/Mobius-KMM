package app.mobius.android.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.mobius.android.app.presentation.navigation.AppDestinations.LOGIN_SCREEN
import app.mobius.android.app.presentation.navigation.AppDestinations.SIGN_UP_SCREEN
import app.mobius.android.app.presentation.navigation.AppDestinations.WELCOME_SCREEN
import app.mobius.android.app.presentation.ui.screen.LoginScreen
import app.mobius.android.app.presentation.ui.screen.SignUpScreen
import app.mobius.android.app.presentation.ui.screen.WelcomeScreen

@Composable
fun AppNavHost() {
    val navHostController = appNavHostController()

    NavHost(
        navController = navHostController,
        startDestination = WELCOME_SCREEN
    ) {
        composable(route = WELCOME_SCREEN) { WelcomeScreen(navHostController = navHostController) }
        composable(route = SIGN_UP_SCREEN) { SignUpScreen() }
        composable(route = LOGIN_SCREEN) { LoginScreen() }
    }
}