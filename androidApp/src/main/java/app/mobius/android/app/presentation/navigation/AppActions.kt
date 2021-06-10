package app.mobius.android.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

class AndroidAppActions(private val androidAppNavHostController: NavHostController) {

    val welcomeScreen: () -> Unit = {
        androidAppNavHostController.navigate(AppDestinations.WELCOME_SCREEN)
    }
    val signUpScreen: () -> Unit = {
        androidAppNavHostController.navigate(AppDestinations.SIGN_UP_SCREEN)
    }
    val loginScreen: () -> Unit = {
        androidAppNavHostController.navigate(AppDestinations.LOGIN_SCREEN)
    }
    val homeScreen: () -> Unit = {
        androidAppNavHostController.navigate(AppDestinations.HOME_SCREEN)
    }

    val upPress: () -> Unit = {
        androidAppNavHostController.navigateUp()
    }

}


@Composable
fun actions(androidAppNavHostController: NavHostController): AndroidAppActions {
    return remember(androidAppNavHostController) {
        AndroidAppActions(androidAppNavHostController)
    }
}