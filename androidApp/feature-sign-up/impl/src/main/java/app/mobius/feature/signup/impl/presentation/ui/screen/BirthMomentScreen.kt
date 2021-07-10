package app.mobius.feature.signup.impl.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
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
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mobius.compose.material.ButtonNext
import app.mobius.feature.signup.impl.R
import app.mobius.feature.signup.impl.presentation.vm.BirthMomentVM
import app.mobius.view.theme.color.Error
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.buttons
import com.vanpra.composematerialdialogs.datetime.datepicker.datepicker
import com.vanpra.composematerialdialogs.datetime.timepicker.timepicker
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@Preview(
    name = "BirthMomentScreen Preview",
    device = Devices.PIXEL_2_XL
)
@Composable
fun BirthMomentScreen(
    onClickNextScreen: () -> Unit = {}  //TODO: Delete default
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

            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (phraseRef, askRef, datePickerRef, dateErrorRef,
                    timePickerRef, timeDescriptionRef, buttonNextRef
                ) = createRefs()

                Phrase(scope = this, phraseRef = phraseRef)
                Ask(scope = this, askRef = askRef, phraseRef = phraseRef)
                DatePicker(scope = this, datePickerRef = datePickerRef, askRef = askRef, viewModel = viewModel)
                DateError(scope = this, dateErrorRef = dateErrorRef, datePickerRef = datePickerRef, viewModel = viewModel)
                TimeDescription(scope = this, timeDescriptionRef = timeDescriptionRef, datePickerRef = datePickerRef, buttonNextRef = buttonNextRef)
                TimePicker(scope = this, timePickerRef = timePickerRef, timeDescriptionRef = timeDescriptionRef, viewModel = viewModel)
                Next(scope = this, buttonNextRef = buttonNextRef, viewModel = viewModel, onClickNextScreen = onClickNextScreen)
            }
        }
    }
}

//TODO: Add as extension
@Composable
private fun Phrase(
    scope: ConstraintLayoutScope,
    phraseRef: ConstrainedLayoutReference,
) {
    scope.apply {
        Text(
            text = stringResource(id = R.string.birth_moment_screen_phrase),
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .wrapContentSize()
                .padding(all = 16.dp)
                .constrainAs(phraseRef) {
                    top.linkTo(parent.top)
                    centerHorizontallyTo(parent)
                }
            ,
            textAlign = TextAlign.Center,
        )
    }
}

//TODO: Add as extension
@Composable
private fun Ask(
    scope: ConstraintLayoutScope,
    askRef: ConstrainedLayoutReference,
    phraseRef: ConstrainedLayoutReference,
) {
    scope.apply {
        Text(
            text = stringResource(id = R.string.birth_moment_screen_ask),
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .wrapContentSize()
                .padding(all = 16.dp)
                .constrainAs(askRef) {
                    top.linkTo(phraseRef.bottom)
                    centerHorizontallyTo(parent)
                }
            ,
            textAlign = TextAlign.Center,
        )
    }
}

/**
 * Source: https://github.com/vanpra/compose-material-dialogs
 */
@ExperimentalUnitApi
@ExperimentalCoroutinesApi
@Composable
private fun DatePicker(
    scope: ConstraintLayoutScope,
    datePickerRef: ConstrainedLayoutReference,
    askRef: ConstrainedLayoutReference,
    viewModel: BirthMomentVM
) {
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

    scope.apply {
        ClickableText(
            text = AnnotatedString(
                text = date.toString(),
            ),
            style = TextStyle.Default.copy(
                fontSize = TextUnit(26F, TextUnitType.Sp)
            ),
            onClick = { dialog.show() },
            modifier = Modifier
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .constrainAs(datePickerRef) {
                    top.linkTo(askRef.bottom)
                    centerHorizontallyTo(parent)
                }
            ,
        )
    }
}

/**
 * visibility on views: https://stackoverflow.com/a/66771589/5279996
 */
@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@Composable
private fun DateError(
    scope: ConstraintLayoutScope,
    dateErrorRef: ConstrainedLayoutReference,
    datePickerRef: ConstrainedLayoutReference,
    viewModel: BirthMomentVM
) {
    val isDateError by viewModel.isDateError.collectAsState()

    scope.apply {
//        TODO: AnimatedVisibility as generic
        AnimatedVisibility(
            visible = isDateError,
            enter = fadeIn(
                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                // Overwrites the default animation with tween
                animationSpec = tween(durationMillis = 250)
            ),
            modifier = Modifier
                .wrapContentSize(align = Alignment.BottomCenter)
                .padding(all = 16.dp)
                .constrainAs(dateErrorRef) {
                    top.linkTo(datePickerRef.bottom)
                    centerHorizontallyTo(parent)
                }
        ) {
            // Content that needs to appear/disappear goes here:
            Text(
                text = stringResource(id = R.string.birth_moment_screen_date_error),
                color = Error,
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@ExperimentalUnitApi
@ExperimentalCoroutinesApi
@Composable
private fun TimePicker(
    scope: ConstraintLayoutScope,
    timePickerRef: ConstrainedLayoutReference,
    timeDescriptionRef: ConstrainedLayoutReference,
    viewModel: BirthMomentVM
) {
    val dialog = remember { MaterialDialog() }
    val time by remember { viewModel.time }.collectAsState()

    dialog.build {

        timepicker {
            viewModel.onTimeChange(it)
        }

        buttons {
            positiveButton(stringResource(id = R.string.actions_ok))
            negativeButton(stringResource(id = R.string.actions_cancel))
        }
    }

    scope.apply {
        ClickableText(
            text = AnnotatedString(
                text = time
            ),
            style = TextStyle.Default.copy(
                fontSize = TextUnit(26F, TextUnitType.Sp)
            ),
            onClick = { dialog.show() },
            modifier = Modifier
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .constrainAs(timePickerRef) {
                    top.linkTo(timeDescriptionRef.bottom)
                    centerHorizontallyTo(parent)
                }
        )
    }
}

@Composable
private fun TimeDescription(
    scope: ConstraintLayoutScope,
    timeDescriptionRef: ConstrainedLayoutReference,
    datePickerRef: ConstrainedLayoutReference,
    buttonNextRef: ConstrainedLayoutReference,
) {
    scope.apply {
        Text(
            text = stringResource(id = R.string.birth_moment_screen_time_description),
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .wrapContentSize(align = Alignment.BottomCenter)
                .padding(all = 16.dp)
                .constrainAs(timeDescriptionRef) {
                    top.linkTo(datePickerRef.bottom)
                    bottom.linkTo(buttonNextRef.top)
                }
            ,
            textAlign = TextAlign.Center,
        )
    }
}

@ExperimentalCoroutinesApi
@Composable
fun Next(
    scope: ConstraintLayoutScope,
    buttonNextRef: ConstrainedLayoutReference,
    viewModel: BirthMomentVM,
    onClickNextScreen: () -> Unit
) {
    scope.apply {
        val isValidData by viewModel.isValidData.collectAsState()
        ButtonNext(
            onClick = {
                onClickNextScreen.invoke()
            },
            modifier = Modifier
                .padding(all = 20.dp)
                .constrainAs(buttonNextRef) {
                    absoluteRight.linkTo(parent.absoluteRight)
                    bottom.linkTo(parent.bottom)
                }
            ,
            enabled = isValidData
        )
    }
}