package app.mobius.android.app.presentation.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import app.mobis.android.feature.welcome.open.presentation.ui.WelcomeNavigation
import app.mobius.android.app.presentation.navigation.AppDestinations.SIGN_UP_SCREEN
import app.mobius.android.app.presentation.navigation.AppDestinations.WELCOME_SCREEN
import app.mobius.android.feature.welcome.impl.wiring.WelcomeModulesProvider

@Composable
fun ComposeNavigation() {
    val navHostController = appNavHostController()

    val currentScreen by navHostController.currentBackStackEntryAsState()

   /* Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(currentScreen?.destination?.id.toString())
                }
            )
        }
    ) {

    }*/

    NavHost(
        navController = navHostController,
        startDestination = WELCOME_SCREEN
    ) {
        composable(route = WELCOME_SCREEN) { WelcomeScreen(navHostController) }
        composable(route = SIGN_UP_SCREEN) { SignUpScreen() }
    }
}

@Composable
fun WelcomeScreen(
    navHostController: NavHostController,
    someText: String = "WELCOME"
) {
/*
    val androidAppActions = AndroidAppActions(androidAppNavHostController = navHostController)

    Row {
        Text(
            text = someText,
            color = Color.Red,
            fontSize = 48.sp,
        )

        Button(
            onClick = {
                androidAppActions.signUpScreen
            }
        ) {
            Text(text = "Navigate to SignUp")
        }
    }
*/

    val welcomeNavigation: WelcomeNavigation = WelcomeModulesProvider.bindWelcomeNavigation()
    return welcomeNavigation.GetWelcomeComposable()   //Has a @Composable
}

@Composable
fun SignUpScreen(someText: String = "SIGN-UP") {
    Text(
        text = someText,
        color = Color.Yellow,
        fontSize = 48.sp,
    )
}