package com.akendardi.cryptowallet.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.akendardi.cryptowallet.navigation.BottomBarNavGraph
import com.akendardi.cryptowallet.presentation.main.bottom_bar.BottomBar

@Composable
fun MainScreenContent(
    navController: NavHostController,
    goToLogInScreen: () -> Unit,
    onProfileClickListener: () -> Unit,
    onCoinClickListener: (symbol: String, name: String) -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->

        BottomBarNavGraph(
            navHostController = navController,
            goToLogInScreen = goToLogInScreen,
            onProfileClickListener = onProfileClickListener,
            onCoinClickListener = onCoinClickListener,
            paddingValues = paddingValues
        )

    }
}