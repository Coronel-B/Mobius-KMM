package app.mobius.view.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import app.mobius.view.theme.color.*

/**
 * Note: secondaryVariant is typically the same as secondary in dark theme since contrast levels are higher, and hence there is less need for a separate secondary color.
 */
@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = ColdColors.PrimaryDark,
    primaryVariant = ColdColors.Primary,
    secondary = WarmColors.SecondaryDark,
    background = Background,
    surface = Surface,
    error = Error,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onBackground = OnBackground,
    onSurface = OnSurface,
    onError = OnError
)

/**
 * OBS: variant should be more dark
 */
@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = ColdColors.PrimaryLight,
    primaryVariant = ColdColors.Primary,
    secondary = WarmColors.SecondaryLight,
    secondaryVariant = WarmColors.Secondary,
    background = Background,
    surface = Surface,
    error = Error,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onBackground = OnBackground,
    onSurface = OnSurface,
    onError = OnError
)

@Composable
fun MobiusTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
//        colors = colors,
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}