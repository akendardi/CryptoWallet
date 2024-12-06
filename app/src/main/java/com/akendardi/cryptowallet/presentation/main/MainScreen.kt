package com.akendardi.cryptowallet.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    goToLogInScreen: () -> Unit,
    onProfileClickListener: () -> Unit,
    onCoinClickListener: (symbol: String, name: String) -> Unit
) {
    val navController = rememberNavController()

    MainScreenContent(
        navController = navController,
        goToLogInScreen = goToLogInScreen,
        onProfileClickListener = onProfileClickListener,
        onCoinClickListener = onCoinClickListener
    )

}
