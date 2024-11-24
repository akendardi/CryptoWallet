package com.akendardi.cryptowallet.presentation.coin_info_screen.handles

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.akendardi.cryptowallet.presentation.coin_info_screen.OperationCoinInfoScreen

@Composable
fun HandleCoinInfoScreen(
    currentOperationScreen: OperationCoinInfoScreen,
    modifier: Modifier = Modifier
) {
    when (currentOperationScreen) {
        OperationCoinInfoScreen.NONE -> {}

        OperationCoinInfoScreen.BUY -> {
            
        }

        OperationCoinInfoScreen.SELL -> {

        }

    }
}