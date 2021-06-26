package app.mobius.feature.signup.impl.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mobius.feature.signup.impl.R
import app.mobius.feature.signup.impl.presentation.vm.FullnameScreenVM
import app.mobius.view.theme.OrangeLight
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@Composable
fun FullnameScreen(onClickNextScreen: () -> Unit) {
    val viewModel: FullnameScreenVM = viewModel()

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
                    FormScreen(viewModel = viewModel)
                }
            }
            Box( modifier = Modifier.align(Alignment.BottomEnd)) { ButtonNext(viewModel, onClickNextScreen) }
        }
    }
}

@Composable
private fun Description() {
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
@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@Composable
private fun FormScreen(viewModel: FullnameScreenVM) {


    val name by remember { viewModel.name }.collectAsState()
    val isNameError by viewModel.isNameError.collectAsState()
    val surname by viewModel.surname.collectAsState()
    val isSurnameError by viewModel.isSurnameError.collectAsState()

    val (focusRequesterFromNameToSurname) = FocusRequester.createRefs()


    Column(
        modifier = Modifier
            .padding(all = 16.dp),
    ) {
        NameContent(
            name = name,
            isError = isNameError,
            focusRequesterToSurname = focusRequesterFromNameToSurname
        ) {
            viewModel.onNameChange(it)
        }
        SurnameContent(
            surname = surname,
            isError = isSurnameError,
            focusRequesterFromName = focusRequesterFromNameToSurname
        ) {
            viewModel.onSurnameChange(it)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun NameContent(
    name: String,
    isError: Boolean = false,
    focusRequesterToSurname: FocusRequester,
    onNameChange: (String) -> Unit
) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        isError = isError,
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = OrangeLight,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {
                focusRequesterToSurname.requestFocus()
            }
        )

    )
}

@ExperimentalComposeUiApi
@Composable
private fun SurnameContent(
    surname: String,
    isError: Boolean = false,
    focusRequesterFromName: FocusRequester,
    onSurnameChange: (String) -> Unit
) {
//    CustomOutlinedTextField(label = stringResource(id = R.string.outlined_text_field_surname))
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = surname,
        onValueChange = onSurnameChange,
        label = { Text("Surname") },
        isError = isError,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester = focusRequesterFromName),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = OrangeLight,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        )
    )

}

//TODO: For Extensions
@ExperimentalCoroutinesApi
@Composable
private fun ButtonNext(viewModel: FullnameScreenVM, onClickNextScreen: () -> Unit) {
    val isValidForm by viewModel.isValidForm.collectAsState()

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