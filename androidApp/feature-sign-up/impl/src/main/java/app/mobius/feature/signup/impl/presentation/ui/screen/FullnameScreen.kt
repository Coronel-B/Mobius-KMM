package app.mobius.feature.signup.impl.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mobius.compose.material.CustomOutlinedTextField
import app.mobius.feature.signup.impl.R
import app.mobius.feature.signup.impl.presentation.vm.FullnameScreenVM
import app.mobius.view.theme.OrangeLight
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
                    FormScreen()
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
fun FormScreen(viewModel: FullnameScreenVM = viewModel()) {

//    val name by viewModel.name.observeAsState("")
    val name2 by rememberSaveable {
        mutableSetOf("")
    }

//    val isNameError by viewModel.isNameError.observeAsState(false)
    val surname by viewModel.surname.observeAsState("")
    val isSurnameError by viewModel.isSurnameError.observeAsState(false)

    Column(
        modifier = Modifier
            .padding(all = 16.dp),
    ) {
//        NameContent(name, isNameError) { viewModel.onNameChange(it) }
        NameContent(name) { viewModel.onNameChange(it) }
        SurnameContent(surname, isSurnameError) { viewModel.onSurnameChange(it) }
    }
}

@Composable
fun NameContent(
    name: String,
    isError: Boolean = false,
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
        )
    )
}

@Composable
fun SurnameContent(
    name: String,
    isError: Boolean = false,
    onSurnameChange: (String) -> Unit
) {
//    CustomOutlinedTextField(label = stringResource(id = R.string.outlined_text_field_surname))

    OutlinedTextField(
        value = name,
        onValueChange = onSurnameChange,
        label = { Text("Surname") },
        isError = isError,
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = OrangeLight,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
        )
    )

}

//TODO: For Extensions
@Composable
fun ButtonNext(onClickNextScreen: () -> Unit) {
    Button(
        onClick = {
            onClickNextScreen.invoke()
              },
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

fun validateForm(name: String, surname: String) : Boolean {
    return false
}