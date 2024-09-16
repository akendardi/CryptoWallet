package com.akendardi.cryptowallet.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.akendardi.cryptowallet.presentation.main.MainScreen


fun NavGraphBuilder.mainNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Screen.MainScreen.route,
        route = Screen.MainScreenNavGraph.route
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen()
        }
    }


}