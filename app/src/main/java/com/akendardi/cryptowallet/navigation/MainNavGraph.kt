package com.akendardi.cryptowallet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akendardi.cryptowallet.presentation.main.SendScreenContent
import com.akendardi.cryptowallet.presentation.main.WalletScreenContent


@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    goToLogInScreen: () -> Unit
) {


    NavHost(navHostController, startDestination = Screen.BottomBarScreen.HomeScreenNavGraph.route) {

        homeNavGraph(
            navHostController,
            goToLogInScreen = goToLogInScreen
        )

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



