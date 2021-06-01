package app.mobius.android.app.presentation.navigation

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.mobis.android.feature.welcome.open.presentation.ui.WelcomeNavigation
import app.mobius.android.feature.welcome.impl.wiring.WelcomeModulesProvider

val welcomeNavigation: WelcomeNavigation = WelcomeModulesProvider.bindWelcomeNavigation()
val welcomeComposable = welcomeNavigation.getWelcomeComposable()

val navHost = NavHost(
    navController = navHostController,
    startDestination = "welcome"
) {
//    TODO: Check if add splash here
    composable(route = "welcome") { welcomeComposable }

}