package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BuyCoinScreen(
    symbol: String,
    modifier: Modifier = Modifier) {

    val viewModel = hiltViewModel<BuyCoinViewModel, BuyCoinViewModel.Factory>(
        creationCallback = { factory -> factory.create(symbol) }
    )
    val state by viewModel.state.collectAsState()

    BuyCoinScreenContent(
        state = state,
        onValueChanged = viewModel::changeAmount,
        onBuyClick = viewModel::buyCoin
    )

}