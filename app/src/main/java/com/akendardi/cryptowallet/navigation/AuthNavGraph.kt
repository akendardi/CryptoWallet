package com.akendardi.cryptowallet.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.akendardi.cryptowallet.presentation.auth.CreateAccountScreen
import com.akendardi.cryptowallet.presentation.auth.LoginScreen
import com.akendardi.cryptowallet.presentation.auth.ResetPassword

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.LoginScreen.route,
        route = Screen.AuthGraph.route
    ) {

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                onRegisterClick = {
                    navController.navigate(Screen.CreateAccountScreen.route)
                },
                onResetPasswordClick = {
                    navController.navigate(Screen.ResetPasswordScreen.route)
                }
            )
        }
        composable(route = Screen.CreateAccountScreen.route) {
            CreateAccountScreen(successRegisterAccount = {
                navController.navigate(Screen.MainScreenNavGraph.route) {
                    popUpTo(Screen.AuthGraph.route) {
                        inclusive = true
                    }

                }
            })
        }
        composable(route = Screen.ResetPasswordScreen.route) {
            ResetPassword(
                onButtonClick = {
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(Screen.LoginScreen.route)
                    }
                }
            )
        }
    }
}