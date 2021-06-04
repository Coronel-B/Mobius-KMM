package app.mobius.android.app.presentation.navigation.screen

import androidx.annotation.StringRes
import app.mobius.R
import app.mobius.android.app.presentation.navigation.AppDestinations

sealed class AppScreen(val route: String, @StringRes val resourceId: Int) {
    object Welcome : AppScreen(AppDestinations.WELCOME_SCREEN, R.string.welcome_screen)
    object SignUp : AppScreen(AppDestinations.SIGN_UP_SCREEN, R.string.sign_up_screen)
    object Login : AppScreen(AppDestinations.LOGIN_SCREEN, R.string.login_screen)
    object Home : AppScreen(AppDestinations.HOME_SCREEN, R.string.home_screen)
}