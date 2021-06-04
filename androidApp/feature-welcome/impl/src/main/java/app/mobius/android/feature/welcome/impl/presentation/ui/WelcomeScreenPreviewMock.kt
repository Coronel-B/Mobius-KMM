package app.mobius.android.feature.welcome.impl.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter

/**
 * 
 * Use preview with params in composable: https://medium.com/snapp-mobile/sample-data-in-compose-previews-bec32b62370f
 * Impl notes: Multiple @PreviewParameter are not allowed
 * Source: https://stackoverflow.com/a/67831364/5279996
 */
@Preview(
    name = "Welcome Screen Preview Mock",
    device = Devices.PIXEL_2_XL
)
@Composable
private fun WelcomeScreenPreviewMock(
    @PreviewParameter(WelcomeScreenPreviewParamProvider::class) welcomeScreenParams: WelcomeScreenParams,
) {
    WelcomeScreenContent(
        onClickSignUp = welcomeScreenParams.onClickSignUp,
        onClickLogin = welcomeScreenParams.onClickLogin
    )
}

data class WelcomeScreenParams(
    val onClickSignUp: () -> Unit,
    val onClickLogin: () -> Unit,
)