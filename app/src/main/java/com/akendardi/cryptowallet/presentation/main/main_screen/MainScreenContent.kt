package com.akendardi.cryptowallet.presentation.main.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            BottomBarNavGraph(
                navHostController = navController,
                goToLogInScreen = goToLogInScreen,
                onProfileClickListener = onProfileClickListener,
                onCoinClickListener = onCoinClickListener
            )
        }

    }
}