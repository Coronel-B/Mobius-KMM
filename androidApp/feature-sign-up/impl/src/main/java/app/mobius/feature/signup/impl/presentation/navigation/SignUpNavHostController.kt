package app.mobius.feature.signup.impl.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun signUpNavHostController() : NavHostController {
    return rememberNavController()
}