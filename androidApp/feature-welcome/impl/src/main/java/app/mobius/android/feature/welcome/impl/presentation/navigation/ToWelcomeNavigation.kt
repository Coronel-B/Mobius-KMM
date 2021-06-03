package app.mobius.android.feature.welcome.impl.presentation.navigation

import androidx.compose.runtime.Composable
import app.mobis.android.feature.welcome.open.presentation.ui.WelcomeNavigation
import app.mobius.android.feature.welcome.impl.presentation.ui.ContentWelcome

class ToWelcomeNavigation : WelcomeNavigation {

    @Composable
    override fun GetWelcomeComposable(onClickSignUp: () -> Unit, onClickLogin: () -> Unit) {
        ContentWelcome(onClickSignUp, onClickLogin)
    }

}