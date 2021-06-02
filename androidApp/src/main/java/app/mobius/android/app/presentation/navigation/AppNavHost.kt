package app.mobius.android.app.presentation.navigation

import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import app.mobis.android.feature.welcome.open.presentation.ui.WelcomeNavigation
import app.mobius.android.app.presentation.navigation.AppDestinations.SIGN_UP_SCREEN
import app.mobius.android.app.presentation.navigation.AppDestinations.WELCOME_SCREEN
import app.mobius.android.feature.welcome.impl.wiring.WelcomeModulesProvider

val welcomeNavigation: WelcomeNavigation = WelcomeModulesProvider.bindWelcomeNavigation()
val welcomeComposable = welcomeNavigation.getWelcomeComposable()

@Composable
fun AppNavHost() {
    val navHostController = appNavHostController()

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

    }

    return NavHost(
        navController = navHostController,
        startDestination = WELCOME_SCREEN
    ) {

//        composable(route = WELCOME_SCREEN) { welcomeComposable }  //TODO
        composable(route = WELCOME_SCREEN) { MockWelcome() }
        composable(route = SIGN_UP_SCREEN) { MockSignUp() }

    }
}

@Composable
fun MockWelcome(someText: String = "WELCOME") {
    val navHostController = appNavHostController()

    Text(
        text = someText,
        color = Color.Black,
        fontSize = 48.sp,
    )

    Button(onClick = { navHostController.navigate(SIGN_UP_SCREEN) }) {
        Text(text = "Navigate to SignUp")
    }
}

@Composable
fun MockSignUp(someText: String = "SIGN-UP") {
    Text(
        text = someText,
        color = Color.Black,
        fontSize = 48.sp,
    )
}