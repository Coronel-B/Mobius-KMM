package app.mobius.feature_signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.R
import android.graphics.Bitmap

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource


@Preview(device = Devices.PIXEL_2_XL)
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
                .fillMaxWidth(),

            ) {
            EnterToYourIdentity()
            CreateYourIdentity()
        }
    }
}

@Composable
fun Logo() {

}

@Composable
fun AppName() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "MÖBIUS",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun EnterToYourIdentity() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
        ),
        modifier = Modifier
            .padding(16.dp)
            .width(140.dp),
    ) {
        Text(
            text = "Ingresa a tu identidad",
            fontSize = 13.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun CreateYourIdentity() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
        ),
        modifier = Modifier
            .padding(16.dp)
            .width(140.dp)
    ) {
        Text(
            text = "Crea tu identidad",
            fontSize = 13.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}