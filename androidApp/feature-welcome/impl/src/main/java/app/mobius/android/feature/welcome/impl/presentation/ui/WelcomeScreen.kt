package app.mobius.android.feature.welcome.impl.presentation.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import app.mobius.view.theme.MobiusTheme

@Composable
fun WelcomeScreen(onClickSignUp: () -> Unit, onClickLogin: () -> Unit) {
    MobiusTheme {
//          A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            WelcomeScreenContent(onClickSignUp, onClickLogin)
        }
    }
}