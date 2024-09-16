package com.akendardi.cryptowallet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akendardi.cryptowallet.presentation.splash.SplashScreen

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        authNavGraph(navController)

        mainNavGraph(navController)

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                successLogin = {
                    navController.navigate(Screen.MainScreenNavGraph.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                },
                unLogin = {
                    navController.navigate(Screen.AuthGraph.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

    }
}