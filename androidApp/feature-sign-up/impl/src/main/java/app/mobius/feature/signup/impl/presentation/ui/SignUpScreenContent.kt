package app.mobius.feature.signup.impl.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import app.mobius.feature.signup.impl.presentation.navigation.SignUpNavHost

@Preview(
    name = "Sign Up Screen Content Preview",
    device = Devices.PIXEL_2_XL
)
@Composable
fun SignUpScreenContent() {
    SignUpNavHost()
}