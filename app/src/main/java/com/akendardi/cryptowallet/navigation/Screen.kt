package com.akendardi.cryptowallet.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object SplashScreen: Screen(ROUTE_SPLASH_SCREEN)

    data object AuthScreen: Screen(ROUTE_AUTH_GRAPH)

    data object HelloScreen: Screen(ROUTE_HELLO_SCREEN)

    data object MainScreenNavGraph: Screen(ROUTE_MAIN_SCREEN_NAV_GRAPH)

    sealed class BottomBarScreen(
        val route: String,
        val icon: ImageVector,
    ){
        data object MainScreen: BottomBarScreen(
            route = ROUTE_MAIN_SCREEN,
            icon = Icons.Default.Home
        )

        data object WalletScreen: BottomBarScreen(
            route = ROUTE_WALLET_SCREEN,
            icon = Icons.Default.Wallet
        )

        data object SendScreen: BottomBarScreen(
            route = ROUTE_SEND_SCREEN,
            icon = Icons.Default.AttachMoney
        )
    }

    private companion object{
        const val ROUTE_SPLASH_SCREEN = "splashScreen"

        const val ROUTE_AUTH_GRAPH= "authScreen"

        const val ROUTE_HELLO_SCREEN= "helloScreen"

        const val ROUTE_MAIN_SCREEN_NAV_GRAPH = "mainScreenNavGraph"
        const val ROUTE_MAIN_SCREEN = "mainScreen"
        const val ROUTE_WALLET_SCREEN = "walletScreen"
        const val ROUTE_SEND_SCREEN = "sendScreen"
    }
}

