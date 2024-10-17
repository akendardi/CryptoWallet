package com.akendardi.cryptowallet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akendardi.cryptowallet.presentation.main.SendScreenContent
import com.akendardi.cryptowallet.presentation.main.WalletScreenContent
import com.akendardi.cryptowallet.presentation.main.home_screen.HomeScreen


@Composable
fun MainNavGraph(
    navHostController: NavHostController
) {


    NavHost(navHostController, startDestination = Screen.BottomBarScreen.HomeScreenNavGraph.route) {

        homeNavGraph(navHostController)

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



