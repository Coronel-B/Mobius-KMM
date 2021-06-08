package app.mobius.android.app.presentation.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import app.mobius.android.feature.welcome.impl.wiring.WelcomeModulesProvider

@Composable
fun LoginScreen() {
    Text(text = "LOGIN", fontSize = 48.sp)
}