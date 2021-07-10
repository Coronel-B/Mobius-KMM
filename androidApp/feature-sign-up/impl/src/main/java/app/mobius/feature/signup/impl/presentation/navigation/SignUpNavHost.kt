package app.mobius.feature.signup.impl.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.mobius.feature.signup.impl.presentation.ui.screen.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@Composable
fun SignUpNavHost() {
    val signUpNavHostController = signUpNavHostController()
    val signUpActions = actions(signUpNavHostController = signUpNavHostController)

    NavHost(
        navController = signUpNavHostController,
        startDestination = SignUpDestinations.FULLNAME_SCREEN   //TODO: Change tu Fullname
    ) {
        composable(route = SignUpDestinations.FULLNAME_SCREEN) {
            FullnameScreen(signUpActions.birthdateScreen)
        }
        composable(route = SignUpDestinations.BIRHDATE_SCREEN) {
            BirthMomentScreen() {
                signUpActions.genderScreen
            }
        }
        composable(route = SignUpDestinations.GENDER_SCREEN) { GenderScreen() }
        composable(route = SignUpDestinations.PHONE_SCREEN) { PhoneScreen() }
        composable(route = SignUpDestinations.PASSWORD_SCREEN) { PasswordScreen() }
        composable(route = SignUpDestinations.LEGALS_SCREEN) { LegalScreen() }
    }
}