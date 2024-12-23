package com.akendardi.cryptowallet.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object SplashScreen : Screen(ROUTE_SPLASH_SCREEN)

    data object AuthScreen : Screen(ROUTE_AUTH_GRAPH)

    data object HelloScreen : Screen(ROUTE_HELLO_SCREEN)

    data object ProfileScreen : Screen(ROUTE_PROFILE_SCREEN)

    data object CoinInfoScreenNavGraph : Screen(ROUTE_COIN_INFO_SCREEN_NAV_GRAPH)
    data object CoinInfoScreen : Screen(ROUTE_COIN_INFO_SCREEN) {
        fun getRouteForDeliverArgs() = "$route/{symbolCoinInfo}/{nameCoinInfo}"

        fun getRoute(symbol: String, name: String) = getRouteForDeliverArgs().replace("{symbolCoinInfo}", symbol)
            .replace("{nameCoinInfo}", name)
    }
    data object CoinInfoBuyingScreen : Screen(ROUTE_COIN_INFO_BUYING_SCREEN){
        fun getRouteForDeliverArgs() = "$route/{symbolCoinInfo}"

        fun getRoute(symbol: String) = getRouteForDeliverArgs().replace("{symbolCoinInfo}", symbol)
    }

    data object CoinInfoSellingScreen : Screen(ROUTE_COIN_INFO_SELLING_SCREEN){
        fun getRouteForDeliverArgs() = "$route/{symbolCoinInfo}"

        fun getRoute(symbol: String) = getRouteForDeliverArgs().replace("{symbolCoinInfo}", symbol)
    }

    data object MainScreenNavGraph : Screen(ROUTE_MAIN_SCREEN_NAV_GRAPH)


    sealed class BottomBarScreen(
        val route: String,
        val icon: ImageVector,
    ) {
        data object HomeScreenNavGraph : BottomBarScreen(
            route = ROUTE_HOME_SCREEN_NAV_GRAPH,
            icon = Icons.Default.Home
        )


        data object HomeScreen : BottomBarScreen(
            route = ROUTE_HOME_SCREEN,
            icon = Icons.Default.Home
        )


        data object WalletScreen : BottomBarScreen(
            route = ROUTE_WALLET_SCREEN,
            icon = Icons.Default.Wallet
        )

        data object TransactionsScreen : BottomBarScreen(
            route = ROUTE_TRANSACTIONS_SCREEN,
            icon = Icons.AutoMirrored.Filled.CompareArrows
        )
    }


    private companion object {
        const val ROUTE_SPLASH_SCREEN = "splashScreen"

        const val ROUTE_AUTH_GRAPH = "authScreen"

        const val ROUTE_HELLO_SCREEN = "helloScreen"

        const val ROUTE_MAIN_SCREEN_NAV_GRAPH = "mainScreenNavGraph"
        const val ROUTE_HOME_SCREEN_NAV_GRAPH = "homeScreenNavGraph"

        const val ROUTE_HOME_SCREEN = "homeScreen"
        const val ROUTE_PROFILE_SCREEN = "profileScreen"

        const val ROUTE_COIN_INFO_SCREEN_NAV_GRAPH = "coinInfoScreenNavGraph"
        const val ROUTE_COIN_INFO_SCREEN = "coinInfoScreen"
        const val ROUTE_COIN_INFO_BUYING_SCREEN = "coinBuyingScreen"
        const val ROUTE_COIN_INFO_SELLING_SCREEN = "coinSellingScreen"

        const val ROUTE_WALLET_SCREEN = "walletScreen"
        const val ROUTE_TRANSACTIONS_SCREEN = "transactionScreen"
    }
}

