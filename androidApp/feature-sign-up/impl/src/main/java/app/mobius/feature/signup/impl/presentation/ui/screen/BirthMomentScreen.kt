package app.mobius.feature.signup.impl.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mobius.feature.signup.impl.R
import app.mobius.feature.signup.impl.presentation.vm.BirthMomentVM
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@Composable
fun BirthMomentScreen(onClickNextScreen: () -> Unit) {  //TODO: Delete default
    val viewModel: BirthMomentVM = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("${stringResource(R.string.feature_sign_up)} - ${stringResource(R.string.birth_moment_screen)}")
                },
                backgroundColor = Color.White,
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Box( modifier = Modifier.align(Alignment.TopCenter)) {
                Column {
                    Phrase()
                    Ask()
                    DatePicker()
                    TimePicker()
                    TimeDescription()
                }
            }
            Box( modifier = Modifier.align(Alignment.BottomEnd)) {
                ButtonNext(viewModel, onClickNextScreen)
            }
        }
    }
}

@Composable
private fun Phrase() {
    Text(
        text = stringResource(id = R.string.birth_moment_screen_phrase),
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

@Composable
private fun Ask() {
    Text(
        text = stringResource(id = R.string.birth_moment_screen_ask),
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

@Composable
private fun DatePicker() {}

@Composable
private fun TimePicker() {}

@Composable
private fun TimeDescription() {
    Text(
        text = stringResource(id = R.string.birth_moment_screen_time_description),
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



//TODO: For Extensions
@ExperimentalCoroutinesApi
@Composable
fun ButtonNext(viewModel: BirthMomentVM, onClickNextScreen: () -> Unit) {
//    val isValidForm by viewModel.isValidForm.collectAsState()
    val isValidForm = false     //TODO: Change

    Button(
        onClick = {
            onClickNextScreen.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
        ),
        modifier = Modifier
            .padding(all = 20.dp),
        enabled = isValidForm
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