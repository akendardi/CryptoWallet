package com.akendardi.cryptowallet.presentation.coin_info_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoinInfoScreen(
    symbol: String,
    name: String,
    onBackButtonClick: () -> Unit,
    onBuyClick: () -> Unit,
    onSellClick: () -> Unit,
) {
    val viewModel = hiltViewModel<CoinInfoViewModel, CoinInfoViewModel.Factory>(
        creationCallback = { factory -> factory.create(symbol = symbol, name = name) }
    )
    val state by viewModel.state.collectAsState()

    CoinInfoScreenContent(
        state = state,
        onTabSelected = viewModel::updateTab,
        onBackButtonClick = onBackButtonClick,
        onBuyClick = onBuyClick,
        onSellClick = onSellClick
    )
}