package app.mobius.android.app.presentation.navigation.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import app.mobis.android.feature.welcome.open.presentation.ui.WelcomeNavigation
import app.mobius.android.app.presentation.navigation.AndroidAppActions
import app.mobius.android.feature.welcome.impl.wiring.WelcomeModulesProvider

@Composable
fun LoginScreen() {
    Text(text = "LOGIN", fontSize = 48.sp)
}