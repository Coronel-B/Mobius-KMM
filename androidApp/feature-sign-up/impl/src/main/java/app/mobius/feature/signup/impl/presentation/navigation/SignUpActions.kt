package app.mobius.feature.signup.impl.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

class SignUpActions(private val signUpNavHostController: NavHostController) {

    val birthdateScreen: () -> Unit = {
        signUpNavHostController.navigate(SignUpDestinations.BIRHDATE_SCREEN)
    }
    val genderScreen: () -> Unit = {
        signUpNavHostController.navigate(SignUpDestinations.GENDER_SCREEN)
    }
    val phoneScreen: () -> Unit = {
        signUpNavHostController.navigate(SignUpDestinations.PHONE_SCREEN)
    }
    val passwordScreen: () -> Unit = {
        signUpNavHostController.navigate(SignUpDestinations.PASSWORD_SCREEN)
    }

    val upPress: () -> Unit = {
        signUpNavHostController.navigateUp()
    }

}


@Composable
fun actions(signUpNavHostController: NavHostController): SignUpActions {
    return remember(signUpNavHostController) {
        SignUpActions(signUpNavHostController)
    }
}