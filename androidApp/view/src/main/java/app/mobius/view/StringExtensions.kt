package app.mobius.view

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import java.util.*


/**
 * Load a string resource to upper case
 *
 * @param id the resource identifier
 * @return the string data associated with the resource
 */
@Composable
@ReadOnlyComposable
fun stringResToUpper(@StringRes id: Int): String {
    return stringResource(id = id).toUpperCase(Locale.getDefault())
}