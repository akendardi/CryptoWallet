package com.akendardi.cryptowallet.navigation

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.akendardi.cryptowallet.presentation.coin_info_screen.CoinInfoScreen
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy.BuyCoinScreen


fun NavGraphBuilder.coinInfoNavGraph(navHostController: NavHostController) {

    navigation(
        startDestination = Screen.CoinInfoScreen.getRouteForDeliverArgs(),
        route = Screen.CoinInfoScreenNavGraph.route
    ) {
        composable(
            route = Screen.CoinInfoScreen.getRouteForDeliverArgs(),
            arguments = listOf(
                navArgument("symbolCoinInfo") { type = NavType.StringType },
                navArgument("nameCoinInfo") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val symbol = backStackEntry.arguments?.getString("symbolCoinInfo")
                ?: throw IllegalArgumentException("Symbol is required")
            val name = backStackEntry.arguments?.getString("nameCoinInfo")
                ?: throw IllegalArgumentException("Name is required")
            CoinInfoScreen(
                symbol = symbol,
                name = name,
                onBackButtonClick = { navHostController.popBackStack() },
                onBuyClick = {
                    navHostController.navigate(Screen.CoinInfoBuyingScreen.getRoute(symbol))
                    Log.d("NAV_TEST", "coinInfoNavGraph: ")
                },
                onSellClick = {}
            )
        }

        composable(
            route = Screen.CoinInfoBuyingScreen.getRouteForDeliverArgs()
        ) { backStackEntry ->
            val symbol = backStackEntry.arguments?.getString("symbolCoinInfo")
                ?: throw IllegalArgumentException("Symbol is required")
            BuyCoinScreen(symbol)
        }
    }

}