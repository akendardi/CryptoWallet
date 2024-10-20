package com.akendardi.cryptowallet.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.akendardi.cryptowallet.presentation.main.home_screen.HomeScreen
import com.akendardi.cryptowallet.presentation.main.profile.Profile

fun NavGraphBuilder.homeNavGraph(navHostController: NavHostController) {
    navigation(
        startDestination = Screen.HomeScreen.route,
        route = Screen.BottomBarScreen.HomeScreenNavGraph.route
    ) {
        composable(
            route = Screen.ProfileScreen.route
        ) {
            Profile(
                onButtonBackClick = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(
            route = Screen.HomeScreen.route,
        ) {
            HomeScreen(
                onProfileClickListener = {
                    navHostController.navigate(
                        route = Screen.ProfileScreen.route,

                        )
                }
            )
        }
    }
}