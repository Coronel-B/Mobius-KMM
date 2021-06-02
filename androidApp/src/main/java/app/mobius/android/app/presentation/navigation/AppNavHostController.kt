package app.mobius.android.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


/**
 * https://stackoverflow.com/a/64603212/5279996
 */
@Composable
fun appNavHostController(): NavHostController {
    return rememberNavController()
}