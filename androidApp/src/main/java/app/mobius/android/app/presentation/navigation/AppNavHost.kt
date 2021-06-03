package app.mobius.android.app.presentation.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import app.mobis.android.feature.welcome.open.presentation.ui.WelcomeNavigation
import app.mobius.android.app.presentation.navigation.AppDestinations.LOGIN_SCREEN
import app.mobius.android.app.presentation.navigation.AppDestinations.SIGN_UP_SCREEN
import app.mobius.android.app.presentation.navigation.AppDestinations.WELCOME_SCREEN
import app.mobius.android.feature.welcome.impl.wiring.WelcomeModulesProvider

@Composable
fun ComposeNavigation() {
    val navHostController = appNavHostController()

    NavHost(
        navController = navHostController,
        startDestination = WELCOME_SCREEN
    ) {
        composable(route = WELCOME_SCREEN) { WelcomeScreen(navHostController) }
        composable(route = SIGN_UP_SCREEN) { SignUpScreen(navHostController) }
        composable(route = LOGIN_SCREEN) { LoginScreen() }
    }
}

@Composable
fun WelcomeScreen(
    navHostController: NavHostController
) {
    val androidAppActions = AndroidAppActions(androidAppNavHostController = navHostController)

    val welcomeNavigation: WelcomeNavigation = WelcomeModulesProvider.bindWelcomeNavigation()
    return welcomeNavigation.GetWelcomeComposable(
        onClickSignUp = androidAppActions.signUpScreen,
        onClickLogin = androidAppActions.loginScreen
    )   //Has a @Composable
}

@Composable
fun SignUpScreen(
    navHostController: NavHostController
) {
    val currentScreen by navHostController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(currentScreen?.destination?.id.toString())
                }
            )
        }
    ) {
        Text(
            text = "Sign Up",
            color = Color.Yellow,
            fontSize = 48.sp,
        )
    }
}

@Composable
fun LoginScreen() {
    Text(text = "LOGIN", fontSize = 48.sp)
}