package app.mobius.feature.signup.impl.presentation.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import app.mobius.compose.material.ButtonNext
import app.mobius.feature.signup.impl.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@Preview(
    name = "GenderScreen Preview",
    device = Devices.PIXEL_2_XL
)
@Composable
fun GenderScreen(
    onClickNextScreen: () -> Unit = {}  //TODO: Delete default
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("${stringResource(R.string.feature_sign_up)} - ${stringResource(R.string.gender_screen)}")
                },
                backgroundColor = Color.White,
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (
                    phraseRef,
                    askRef,
                    checkboxRef,
                    buttonNextRef
                ) = createRefs()

                Phrase(scope = this, phraseRef = phraseRef)
                Ask(scope = this, askRef = askRef, phraseRef = phraseRef)
                Checkbox(scope = this, checkboxRef = checkboxRef)
                Next(scope = this, buttonNextRef = buttonNextRef, onClickNextScreen = onClickNextScreen)
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
            text = stringResource(id = R.string.gender_screen_phrase),
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


@Composable
private fun Checkbox(
    scope: ConstraintLayoutScope,
    checkboxRef: ConstrainedLayoutReference,
) {

}

@ExperimentalCoroutinesApi
@Composable
fun Next(
    scope: ConstraintLayoutScope,
    buttonNextRef: ConstrainedLayoutReference,
    onClickNextScreen: () -> Unit
) {
    scope.apply {
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
        )
    }
}