package app.mobius.feature_signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(device = Devices.PIXEL_2)
@Composable
fun PreviewWelcome() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White)
    ) {
        Logo()
        AppName()

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
            ,

        ) {
            EnterToYourIdentity()
            CreateYourIdentity()
        }
    }
}

@Composable
fun Logo() {
    Text(text = "LOGO", color = Color.Black)
}

@Composable
fun AppName() {
    Text(
        text = "Möbius",
        color = Color.Black,
        fontFamily = FontFamily.SansSerif,
        fontSize = 52.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun EnterToYourIdentity() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
        ),
    ) {
        Text(
            text = "Ingresa a tu identidad",
            color = Color.Black,
        )

    }
}

@Composable
fun CreateYourIdentity() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
        )
    ) {
        Text(text = "Crea tu identidad", color = Color.Black)
    }
}