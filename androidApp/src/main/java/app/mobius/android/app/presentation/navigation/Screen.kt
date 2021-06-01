package app.mobius.android.app.presentation.navigation

import androidx.annotation.StringRes
import app.mobius.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Welcome : Screen(AppDestinations.WELCOME_SCREEN, R.string.welcome_screen)
    object SignUp : Screen(AppDestinations.SIGN_UP_SCREEN, R.string.sign_up_screen)
}