package com.akendardi.cryptowallet.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.akendardi.cryptowallet.presentation.coin_info_screen.CoinInfoScreen
import com.akendardi.cryptowallet.presentation.main.main_screen.MainScreen
import com.akendardi.cryptowallet.presentation.profile.Profile


fun NavGraphBuilder.mainNavGraph(
    navHostController: NavHostController,
    goToLogInScreen: () -> Unit
) {
    navigation(
        route = Screen.MainScreenNavGraph.route,
        startDestination = Screen.BottomBarScreen.HomeScreenNavGraph.route
    ) {

        composable(
            route = Screen.BottomBarScreen.HomeScreenNavGraph.route
        ) {
            MainScreen(
                goToLogInScreen = goToLogInScreen,
                onProfileClickListener = {
                    navHostController.navigate(Screen.ProfileScreen.route)
                },
                onCoinClickListener = {
                    navHostController.navigate(Screen.CoinInfoScreen.getRoute(it))
                }
            )
        }

        composable(
            route = Screen.ProfileScreen.route
        ) {
            Profile(
                onButtonBackClick = { navHostController.popBackStack() },
                goToLogInScreen = goToLogInScreen,
            )
        }
        composable(
            route = Screen.CoinInfoScreen.getRouteForDeliverArgs()
        ) {
            val symbol =
                it.arguments?.getString("symbolCoinInfo") ?: throw Exception("Symbol is null")
            CoinInfoScreen(
                symbol = symbol
            )
        }

    }
}



