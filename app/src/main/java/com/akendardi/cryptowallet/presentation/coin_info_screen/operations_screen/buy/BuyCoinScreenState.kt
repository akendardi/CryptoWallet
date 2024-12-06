package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy

import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.components.OperationResult

data class BuyCoinScreenState(
    val isFirstLoading: Boolean = true,
    val isCanBuy: Boolean = false,
    val name: String = "",
    val symbol: String = "",
    val imageUrl: String = "",
    val currentPrice: String = "0.0$",
    val count: String = "",
    val currentFreeBalance: String = "0.0$",
    val totalCount: String = "0.0$",
    val error: String = "",
    val operationResult: OperationResult = OperationResult.Initial
)
