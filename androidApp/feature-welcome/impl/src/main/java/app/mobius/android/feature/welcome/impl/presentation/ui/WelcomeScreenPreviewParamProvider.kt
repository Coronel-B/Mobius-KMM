package app.mobius.android.feature.welcome.impl.presentation.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

    class WelcomeScreenPreviewParamProvider : PreviewParameterProvider<WelcomeScreenParams> {
    override val values: Sequence<WelcomeScreenParams>
        get() = sequenceOf(
            WelcomeScreenParams( onClickSignUp = {}, onClickLogin = {})
        )

    override val count: Int
        get() = super.count

}