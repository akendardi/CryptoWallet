package com.akendardi.cryptowallet.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akendardi.cryptowallet.presentation.main.wallet_screen.WalletScreen
import com.akendardi.cryptowallet.presentation.main.home_screen.HomeScreen
import com.akendardi.cryptowallet.presentation.main.transactions_screen.TransactionsScreen
import com.akendardi.cryptowallet.presentation.main.wallet_screen.WalletScreenContent

@Composable
fun BottomBarNavGraph(
    navHostController: NavHostController,
    goToLogInScreen: () -> Unit,
    onProfileClickListener: () -> Unit,
    onCoinClickListener: (symbol: String, name: String) -> Unit,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        route = Screen.BottomBarScreen.HomeScreenNavGraph.route,
        startDestination = Screen.BottomBarScreen.HomeScreen.route,
    ) {
        composable(
            route = Screen.BottomBarScreen.HomeScreen.route,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                HomeScreen(
                    onProfileClickListener = onProfileClickListener,
                    onCoinClickListener = onCoinClickListener,
                    logout = goToLogInScreen
                )
            }
        }

        composable(
            route = Screen.BottomBarScreen.TransactionsScreen.route,
        ) {
            Box(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
                TransactionsScreen()
            }

        }

        composable(
            route = Screen.BottomBarScreen.WalletScreen.route,
        ) {
            Box(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
                WalletScreen(
                    onCoinClick = onCoinClickListener
                )
            }
        }
    }
}