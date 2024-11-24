package com.akendardi.cryptowallet.presentation.coin_info_screen.information

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoinInfoInformationScreen(
    symbol: String,
    onBuyClick: () -> Unit,
    onSellClick: () -> Unit
) {

    val viewModel =
        hiltViewModel<CoinInfoInformationViewModel, CoinInfoInformationViewModel.Factory>(
            creationCallback = { factory -> factory.create(symbol) }
        )

    val state by viewModel.state.collectAsState()

    CoinInfoInformationScreenContent(
        state = state,
        onGraphTypeChange = viewModel::changeGraphType,
        onRefresh = viewModel::loadData,
        onBuyClick = onBuyClick,
        onSellClick = onSellClick
    )
}