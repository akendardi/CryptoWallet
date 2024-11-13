package com.akendardi.cryptowallet.navigation

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akendardi.cryptowallet.presentation.main.SendScreenContent
import com.akendardi.cryptowallet.presentation.main.WalletScreenContent
import com.akendardi.cryptowallet.presentation.main.coin_info_screen.CoinInfoScreen


@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    goToLogInScreen: () -> Unit
) {


    NavHost(
        navHostController,
        startDestination = Screen.BottomBarScreen.HomeScreenNavGraph.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {

        homeNavGraph(
            navHostController,
            onCoinClickListener = {
                navHostController.navigate(route = Screen.CoinInfoScreen.getRoute(it)){
                    launchSingleTop = true
                }
            },
            goToLogInScreen = goToLogInScreen
        )

        composable(
            route = Screen.CoinInfoScreen.getRouteForDeliverArgs()
        ) {
            val symbol = it.arguments?.getString("symbol") ?: throw Exception("Symbol is null")
            Log.d("NAVIGATION", "MainNavGraph: $symbol")
            CoinInfoScreen()
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



