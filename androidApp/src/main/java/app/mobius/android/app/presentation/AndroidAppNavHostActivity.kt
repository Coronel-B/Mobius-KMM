package app.mobius.android.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import app.mobis.android.feature.welcome.open.presentation.ui.WelcomeNavigation
import app.mobius.android.feature.welcome.impl.wiring.WelcomeModulesProvider
import app.mobius.view.theme.MobiusTheme

class AndroidAppNavHostActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val welcomeNavigation: WelcomeNavigation = WelcomeModulesProvider.bindWelcomeNavigation()


        /*setContent {
            MobiusTheme {
                Surface(color = MaterialTheme.colors.background) {

                    val welcomeNavigation: WelcomeNavigation = WelcomeModulesProvider.bindWelcomeNavigation()
//                    TestCompose(welcomeNavigation.getWelcomeComposable())
                }
            }
        }*/
    }
}

@Preview(showBackground = true)
@Composable
fun TestCompose(someText: String = "MÖBIUS") {
    Text(
        text = someText,
        color = Color.Black,
        fontSize = 48.sp,
    )
}