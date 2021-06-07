package app.mobius.feature.signup.impl.presentation.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import app.mobius.view.theme.MobiusTheme

@Preview(
    name = "Sign Up Screen Preview",
    device = Devices.PIXEL_2_XL
)
@Composable
fun SignUpScreen() {
    MobiusTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            SignUpScreenContent()
        }
    }
}