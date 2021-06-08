package app.mobius.feature.signup.impl.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.mobius.feature.signup.impl.R
import java.util.*

@Preview(
    name = "FullnameScreen Preview",
    device = Devices.PIXEL_2_XL
)
@Composable
fun FullnameScreen(onClickNextScreen: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("${stringResource(R.string.feature_sign_up)} - ${stringResource(R.string.fullname_screen)}")
                },
                backgroundColor = Color.White,
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.fullname_screen_description),
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(all = 16.dp),
                textAlign = TextAlign.Center,
            )

            Box( modifier = Modifier.align(Alignment.BottomEnd)) {
                ButtonNext(onClickNextScreen)
            }

        }
    }
}

//TODO: For Extensions
@Composable
fun ButtonNext(onClickNextScreen: () -> Unit) {
    Button(
        onClick = { onClickNextScreen.invoke() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
        ),
        modifier = Modifier.padding(all = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.actions_next).toUpperCase(Locale.getDefault()),
            color = Color.Black,
            fontFamily = FontFamily.Default,
            fontSize = 18.sp,
            style = typography.h3
        )
    }
}