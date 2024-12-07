package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.sell

import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.components.OperationResult

data class SellCoinScreenState(
    val isFirstLoading: Boolean = true,
    val isCanBuy: Boolean = false,
    val name: String = "",
    val symbol: String = "",
    val imageUrl: String = "",
    val currentPrice: String = "0.0$",
    val countValue: String = "",
    val totalCount: String = "",
    val usersBalanceForCurrentCoin: String = "0.0$",
    val usersCoinsCount: String = "",
    val error: String = "",
    val operationResult: OperationResult = OperationResult.Initial
)