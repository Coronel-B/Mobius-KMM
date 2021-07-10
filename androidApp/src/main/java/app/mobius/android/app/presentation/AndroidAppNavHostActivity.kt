package app.mobius.android.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import app.mobius.android.app.presentation.navigation.AppNavHost
import app.mobius.view.theme.MobiusTheme

@ExperimentalUnitApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
class AndroidAppNavHostActivity : ComponentActivity() {

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