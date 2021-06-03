package app.mobius.android.app.presentation.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

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
        startDestination = "WELCOME_SCREEN"
    ) {

//        composable(route = WELCOME_SCREEN) { welcomeComposable }  //TODO
        composable(route = "WELCOME_SCREEN") { MockWelcome(navHostController) }
        composable(route = "SIGN_UP_SCREEN") { MockSignUp() }

    }
}

@Composable
fun MockWelcome(
    navHostController: NavController,
    someText: String = "WELCOME"
) {

//    val welcomeNavigation: WelcomeNavigation = WelcomeModulesProvider.bindWelcomeNavigation()
//    val welcomeComposable = welcomeNavigation.getWelcomeComposable()


    Row {
        Text(
            text = someText,
            color = Color.Red,
            fontSize = 48.sp,
        )

        Button(onClick = { navHostController.navigate("SIGN_UP_SCREEN") }) {
            Text(text = "Navigate to SignUp")
        }
    }

}

@Composable
fun MockSignUp(someText: String = "SIGN-UP") {
    Text(
        text = someText,
        color = Color.Yellow,
        fontSize = 48.sp,
    )
}