package app.mobius.feature.signup.impl.presentation.ui.screen.mock

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import app.mobius.feature.signup.impl.presentation.ui.screen.BirthMomentScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalUnitApi
@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@Preview(
    name = "BirthMomentScreen Preview",
    device = Devices.PIXEL_2_XL
)
@Composable
private fun BirthMomentPreviewMock(onClickNextScreen: () -> Unit = {}) {
    BirthMomentScreen(onClickNextScreen = onClickNextScreen)
}