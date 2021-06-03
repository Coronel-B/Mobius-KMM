package app.mobius.android.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import app.mobius.android.app.presentation.navigation.ComposeNavigation
import app.mobius.view.theme.MobiusTheme

class AndroidAppNavHostActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobiusTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ComposeNavigation()
                }
            }
        }

    }
}