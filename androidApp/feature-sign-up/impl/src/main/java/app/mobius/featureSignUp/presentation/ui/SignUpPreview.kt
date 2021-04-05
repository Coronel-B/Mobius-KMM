package app.mobius.featureSignUp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    device = Devices.PIXEL_2_XL
)
@Composable
fun PreviewWelcome() {
    MaterialTheme {
        val typography = MaterialTheme.typography
        Column(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Sign Up with Compose")
        }
    }
}