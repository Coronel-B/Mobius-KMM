package app.mobius.android.app.presentation.navigation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import app.mobis.android.feature.welcome.open.presentation.ui.WelcomeNavigation
import app.mobius.android.app.presentation.navigation.AndroidAppActions
import app.mobius.android.feature.welcome.impl.wiring.WelcomeModulesProvider

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