package app.mobius.android.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import app.mobius.android.app.presentation.navigation.AppNavHost
import app.mobius.view.theme.MobiusTheme

class AndroidAppNavHostActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobiusTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppNavHost()
                }
            }
        }

    }
}