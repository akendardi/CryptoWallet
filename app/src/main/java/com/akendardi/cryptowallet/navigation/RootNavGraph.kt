package com.akendardi.cryptowallet.navigation

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akendardi.cryptowallet.presentation.auth.auth_screen.AuthScreen
import com.akendardi.cryptowallet.presentation.auth.hello_screen.HelloScreen
import com.akendardi.cryptowallet.presentation.main.MainScreen
import com.akendardi.cryptowallet.presentation.splash.SplashScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable(route = Screen.AuthScreen.route) {
            AuthScreen(
                goToMainScreen = {
                    navigateAndDeleteOldScreen(
                        navController,
                        newScreen = Screen.MainScreenNavGraph,
                        oldScreen = Screen.AuthScreen
                    )
                    Log.d("Navigation", "Navigating to MainScreenNavGraph")
                },
                goToHelloScreen = {
                    navigateAndDeleteOldScreen(
                        navController,
                        newScreen = Screen.HelloScreen,
                        oldScreen = Screen.AuthScreen
                    )
                    Log.d("Navigation", "Navigating to HelloScreen")
                }
            )
        }

        composable(route = Screen.HelloScreen.route) {
            HelloScreen(
                onButtonClick = {
                    navigateAndDeleteOldScreen(
                        navController,
                        newScreen = Screen.MainScreenNavGraph,
                        oldScreen = Screen.HelloScreen
                    )
                }
            )
        }

        composable(route = Screen.MainScreenNavGraph.route) {
            MainScreen(
                goToLogInScreen = {
                    navigateAndDeleteOldScreen(
                        navController,
                        newScreen = Screen.AuthScreen,
                        oldScreen = Screen.MainScreenNavGraph
                    )
                }
            )
        }

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                successLogin = {
                    navigateAndDeleteOldScreen(
                        navController,
                        newScreen = Screen.MainScreenNavGraph,
                        oldScreen = Screen.SplashScreen
                    )
                    Log.d("Navigation", "Navigating to MainScreenNavGraph")
                },
                unLogin = {
                    navigateAndDeleteOldScreen(
                        navController,
                        newScreen = Screen.AuthScreen,
                        oldScreen = Screen.SplashScreen
                    )
                }
            )
        }

    }
}

private fun navigateAndDeleteOldScreen(
    navController: NavHostController,
    newScreen: Screen,
    oldScreen: Screen
){
    navController.navigate(newScreen.route) {
        popUpTo(oldScreen.route) {
            inclusive = true
        }
    }
}