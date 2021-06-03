package app.mobius.android.feature.welcome.impl.presentation.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.mobius.view.theme.MobiusTheme

@Preview(showBackground = true)
@Composable
fun ContentWelcome() {
    MobiusTheme {
//          A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            PreviewWelcome()
        }
    }
}