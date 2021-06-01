package app.mobius.android.feature.welcome.impl.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.mobius.view.theme.MobiusTheme

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentWelcome()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentWelcome() {
    MobiusTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            PreviewWelcome()
        }
    }
}

@Composable
fun DefaultPreview() {
    MobiusTheme {
        PreviewWelcome()
    }
}