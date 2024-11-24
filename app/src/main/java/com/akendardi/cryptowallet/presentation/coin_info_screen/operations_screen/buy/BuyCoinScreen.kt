package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AlertDialogBuyCoin(
    symbol: String,
    modifier: Modifier = Modifier) {

    val viewModel = hiltViewModel<BuyCoinViewModel, BuyCoinViewModel.Factory>(
        creationCallback = { factory -> factory.create(symbol) }
    )

}