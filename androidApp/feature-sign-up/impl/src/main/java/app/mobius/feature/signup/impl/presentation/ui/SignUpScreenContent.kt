package app.mobius.feature.signup.impl.presentation.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import app.mobius.feature.signup.impl.presentation.navigation.SignUpNavHost
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalComposeUiApi
@Preview(
    name = "Sign Up Screen Content Preview",
    device = Devices.PIXEL_2_XL
)
@Composable
fun SignUpScreenContent() {
    SignUpNavHost()
}