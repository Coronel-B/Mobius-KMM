package app.mobius.feature.signup.impl.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mobius.feature.signup.impl.R
import app.mobius.feature.signup.impl.presentation.vm.BirthMomentVM
import app.mobius.view.stringResToUpper
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.buttons
import com.vanpra.composematerialdialogs.datetime.datepicker.datepicker
import com.vanpra.composematerialdialogs.datetime.timepicker.timepicker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@ExperimentalUnitApi
@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@Preview(
    name = "BirthMomentScreen Preview",
    device = Devices.PIXEL_2_XL
)
@Composable
fun BirthMomentScreen(
    onClickNextScreen: () -> Unit = {}
) {
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
                    DatePicker(viewModel = viewModel)
                    TimePicker(viewModel = viewModel)
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
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Light,
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

/**
 * Source: https://github.com/vanpra/compose-material-dialogs
 */
@ExperimentalUnitApi
@ExperimentalCoroutinesApi
@Composable
private fun DatePicker(viewModel: BirthMomentVM) {
    val dialog = remember { MaterialDialog() }

    val date by remember { viewModel.date }.collectAsState()

    dialog.build {
        datepicker {
            viewModel.onDateChange(it)
        }
        buttons {
            positiveButton(stringResource(id = R.string.actions_ok))
            negativeButton(stringResource(id = R.string.actions_cancel))
        }
    }

    ClickableText(
        text = AnnotatedString(
            text = date.toString(),
        ),
        style = TextStyle.Default.copy(
            fontSize = TextUnit(26F, TextUnitType.Sp)
        ),
        onClick = { dialog.show() },
        modifier = Modifier
            .wrapContentWidth(align = Alignment.CenterHorizontally),

    )
}

@ExperimentalUnitApi
@ExperimentalCoroutinesApi
@Composable
private fun TimePicker(viewModel: BirthMomentVM) {

    val dialog = remember { MaterialDialog() }
    val time by remember { viewModel.time }.collectAsState()

    dialog.build {

        timepicker {
            viewModel.onTimeChange(it)
        }
//        colorpick

        buttons {
            positiveButton(stringResource(id = R.string.actions_ok))
            negativeButton(stringResource(id = R.string.actions_cancel))
        }
    }

    ClickableText(
        text = AnnotatedString(
            text = time
        ),
        style = TextStyle.Default.copy(
            fontSize = TextUnit(26F, TextUnitType.Sp)
        ),
        onClick = { dialog.show() },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
private fun TimeDescription() {
    Text(
        text = stringResource(id = R.string.birth_moment_screen_time_description),
        color = Color.Black,
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
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
            text = stringResToUpper(id = R.string.actions_next),
            color = Color.Black,
            fontFamily = FontFamily.Default,
            fontSize = 18.sp,
            style = typography.h3
        )
    }
}