package app.mobius.feature.signup.impl.presentation.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun SignUpScreenContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Title top app bar")
                }
            )
        }
    ) {
        Text(
            text = "Sign Up",
            color = Color.Black,
            fontSize = 48.sp,
        )
    }
}