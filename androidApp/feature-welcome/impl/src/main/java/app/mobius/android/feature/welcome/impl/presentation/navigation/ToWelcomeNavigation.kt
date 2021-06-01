package app.mobius.android.feature.welcome.impl.presentation.navigation

import androidx.activity.ComponentActivity
import app.mobis.android.feature.welcome.open.presentation.ui.WelcomeNavigation
import app.mobius.android.feature.welcome.impl.presentation.ui.WelcomeActivity

class ToWelcomeNavigation : WelcomeNavigation {

    override fun getWelcomeComposable(): ComponentActivity {
        return WelcomeActivity()
    }

}