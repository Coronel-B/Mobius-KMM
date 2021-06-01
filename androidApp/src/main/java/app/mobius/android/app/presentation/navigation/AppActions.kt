package app.mobius.android.app.presentation.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

class AndroidAppActions(private val androidAppNavHostController: NavHostController) {

    val mainScreen: () -> Unit = {
        androidAppNavHostController.navigate(AppDestinations.MAIN_SCREEN)
    }

    val welcomeScreen: (String) -> Unit = { title ->
        androidAppNavHostController.navigate("${AppDestinations.WELCOME_SCREEN}/$title")
    }

    val upPress: () -> Unit = {
        androidAppNavHostController.navigateUp()
    }

}

val actions = remember(navHostController) {
    AndroidAppActions(navHostController)
}