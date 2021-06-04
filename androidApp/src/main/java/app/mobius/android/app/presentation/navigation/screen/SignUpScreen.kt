package app.mobius.android.app.presentation.navigation.screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import app.mobis.android.feature.welcome.open.presentation.ui.WelcomeNavigation
import app.mobius.android.app.presentation.navigation.AndroidAppActions
import app.mobius.android.feature.welcome.impl.wiring.WelcomeModulesProvider

@Composable
fun SignUpScreen(
    navHostController: NavHostController
) {
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
        Text(
            text = "Sign Up",
            color = Color.Yellow,
            fontSize = 48.sp,
        )
    }
}
