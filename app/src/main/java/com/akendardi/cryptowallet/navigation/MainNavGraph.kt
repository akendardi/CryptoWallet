package com.akendardi.cryptowallet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akendardi.cryptowallet.presentation.main.main_screen_content.MainScreenContent
import com.akendardi.cryptowallet.presentation.main.SendScreenContent
import com.akendardi.cryptowallet.presentation.main.WalletScreenContent


@Composable
fun MainNavGraph(
    navHostController: NavHostController
) {


    NavHost(navHostController, startDestination = Screen.BottomBarScreen.MainScreen.route) {
        composable(
            route = Screen.BottomBarScreen.MainScreen.route,
        ) {
            MainScreenContent()
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



