package com.akendardi.cryptowallet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akendardi.cryptowallet.presentation.main.SendScreenContent
import com.akendardi.cryptowallet.presentation.main.WalletScreenContent
import com.akendardi.cryptowallet.presentation.main.home_screen.HomeScreen

@Composable
fun BottomBarNavGraph(
    navHostController: NavHostController,
    goToLogInScreen: () -> Unit,
    onProfileClickListener: () -> Unit,
    onCoinClickListener: (symbol: String, name: String) -> Unit
) {
    NavHost(
        navController = navHostController,
        route = Screen.BottomBarScreen.HomeScreenNavGraph.route,
        startDestination = Screen.BottomBarScreen.HomeScreen.route
    ) {
        composable(
            route = Screen.BottomBarScreen.HomeScreen.route,
        ) {
            HomeScreen(
                onProfileClickListener = onProfileClickListener,
                onCoinClickListener = onCoinClickListener,
                logout = goToLogInScreen
            )
        }

        composable(
            route = Screen.BottomBarScreen.SendScreen.route,
        ) {
            SendScreenContent()
        }

        composable(
            route = Screen.BottomBarScreen.WalletScreen.route,
        ) {
            WalletScreenContent()
        }
    }
}