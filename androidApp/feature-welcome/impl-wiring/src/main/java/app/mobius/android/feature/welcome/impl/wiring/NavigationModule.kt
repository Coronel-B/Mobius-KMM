package app.mobius.android.feature.welcome.impl.wiring

import app.mobius.android.feature.welcome.open.presentation.ui.WelcomeNavigation
import app.mobius.android.feature.welcome.impl.presentation.navigation.ToWelcomeNavigation

val welcomeNavigation: WelcomeNavigation = ToWelcomeNavigation()