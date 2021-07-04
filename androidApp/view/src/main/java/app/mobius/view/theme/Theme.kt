package app.mobius.view.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import app.mobius.view.theme.color.*

private val DarkColorPalette = darkColors()

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = WarmColors.Secondary,
    primaryVariant = ColorForResearch,
    secondary = ColdColors.Primary,
    secondaryVariant = ColorForResearch,
    background = White,
    surface = White,
    error = Color.Red,
    onPrimary = ColdColors.Primary,
    onSecondary = WarmColors.SecondaryLight,
    onBackground = Black,
    onSurface = WarmColors.Secondary,
    onError = ColorForResearch
)

@Composable
fun MobiusTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}