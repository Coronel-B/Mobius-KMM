package app.mobius.feature.signup.impl.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    val name by viewModel.name.observeAsState("")

    Column(
        modifier = Modifier
            .padding(all = 16.dp),
    ) {
        NameContent(name) { viewModel.onNameChange(it) }
        Surname()
    }
}

@Composable
fun NameContent(name: String, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
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
fun Surname() {
    CustomOutlinedTextField(label = stringResource(id = R.string.outlined_text_field_surname))
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

//TODO: Use watchers for textfields
fun validateForm(name: String, surname: String) : Boolean {
    return false
}