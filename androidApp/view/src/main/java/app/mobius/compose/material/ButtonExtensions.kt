package app.mobius.compose.material

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import app.mobius.view.R
import app.mobius.view.stringResToUpper

@Composable
fun ButtonNext(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean = true,
) {

    Button(
        onClick = {
            onClick.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            disabledBackgroundColor = MaterialTheme.colors.primary,
        ),
        modifier = modifier,
        enabled = enabled
    ) {
        Text(
            text = stringResToUpper(id = R.string.actions_next),
            color = if (enabled) Color.Black else Color.White,
            fontFamily = FontFamily.Default,
            fontSize = 18.sp,
            style = MaterialTheme.typography.h3
        )
    }
}