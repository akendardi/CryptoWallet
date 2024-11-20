package com.akendardi.cryptowallet.presentation.coin_info_screen.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoinInfoTransactionsScreen(
    symbol: String,
    modifier: Modifier = Modifier
) {

    val viewModel =
        hiltViewModel<CoinInfoTransactionsViewModel, CoinInfoTransactionsViewModel.Factory>(
            creationCallback = { factory -> factory.create(symbol) }
        )

    Box(Modifier.fillMaxSize().background(Color.Blue))
}