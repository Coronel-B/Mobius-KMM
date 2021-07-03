package app.mobius.android.feature.welcome.impl.presentation.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*
import androidx.compose.material.Typography
import androidx.compose.ui.res.stringResource
import app.mobius.android.feature.welcome.impl.R
import app.mobius.view.stringResToUpper

@Composable
fun WelcomeScreenContent(
    onClickSignUp: () -> Unit,
    onClickLogin: () -> Unit
) {
    MaterialTheme {
        val typography = MaterialTheme.typography
        Column(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            LogoImage()
            AppNameText()
            DividerCanvas()
            ContentButtons(typography = typography, onClickSignUp = onClickSignUp, onClickLogin = onClickLogin)
        }
    }
}

@Composable
fun LogoImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_welcome_logo),
        contentDescription = null,
        modifier = Modifier
            .wrapContentWidth()
            .padding(
                top = 8.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp,
            ),
        contentScale = ContentScale.Inside
    )
}

@Composable
fun AppNameText() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResToUpper(id = R.string.app_name),
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun DividerCanvas() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 50.dp,
                end = 50.dp,
                top = 16.dp,
                bottom = 16.dp
            )
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

//        Top-Right is (0f; 0f)
        drawLine(
            start = Offset(x = canvasWidth, y =  0f),
            end = Offset(x= 0f, y = canvasHeight),
            color = Color.DarkGray
        )
    }
}

@Composable
fun ContentButtons(
    typography: Typography,
    onClickSignUp: () -> Unit,
    onClickLogin: () -> Unit
) {
//    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(
                bottom = 16.dp
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
//        , horizontalArrangement = Arrangement.SpaceEvenly // TODO: Does not work in Row
        ) {
        CreateYourIdentityBtn(typography, onClickSignUp)
        EnterToYourIdentityBtn(typography, onClickLogin)
    }
}

@Composable
fun CreateYourIdentityBtn(typography: Typography, onClickSignUp: () -> Unit) {
    Button(
        onClick = { onClickSignUp.invoke() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
        ),
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = stringResToUpper(id = R.string.create_your_identity),
            fontSize = 13.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = typography.h4
        )
    }
}

@Composable
fun EnterToYourIdentityBtn(typography: Typography, onClickLogin: () -> Unit) {
    Button(
        onClick = { onClickLogin.invoke() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
        ),
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = stringResToUpper(id = R.string.enter_to_your_identity),
            color = Color.Black,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = typography.h4
        )
    }
}