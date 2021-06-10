package app.mobius.feature.signup.impl.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.mobius.compose.material.CustomOutlinedTextField
import app.mobius.feature.signup.impl.R
import java.util.*

@Composable
fun FullnameScreen(onClickNextScreen: () -> Unit) {
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

            Box( modifier = Modifier.align(Alignment.Center)) {
                Column() {
                    Description()
                    Form()
                }
            }
            Box( modifier = Modifier.align(Alignment.BottomEnd)) { ButtonNext(onClickNextScreen) }
        }
    }
}

@Composable
fun Description() {
    Text(
        text = stringResource(id = R.string.fullname_screen_description),
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .wrapContentSize()
            .padding(all = 16.dp),
        textAlign = TextAlign.Center,
    )
}

/**
 * Imports: https://stackoverflow.com/a/67882258/5279996
 */
@Composable
fun Form() {
    Column(
        modifier = Modifier
            .padding(all = 16.dp),
    ) {
        Name()
        Surname()
    }
}

@Composable
fun Name() {
    CustomOutlinedTextField(label = stringResource(id = R.string.outlined_text_field_name))
}

@Composable
fun Surname() {
    CustomOutlinedTextField(label = stringResource(id = R.string.outlined_text_field_surname))
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