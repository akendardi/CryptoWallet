package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy

data class BuyCoinScreenState(
    val isFirstLoading: Boolean = true,
    val isOperationLoading: Boolean = false,
    val isCanBuy: Boolean = false,
    val name: String = "",
    val symbol: String = "",
    val imageUrl: String = "",
    val currentPrice: String = "0.0$",
    val amount: String = "",
    val currentFreeBalance: String = "0.0$",
    val totalCount: String = "0.0$",
    val error: String = "",
    val operationResult: BuyOperationResult = BuyOperationResult.Initial
)

sealed class BuyOperationResult{

    data object Initial: BuyOperationResult()

    data object Error: BuyOperationResult()

    data class Success(val transactionId: Int): BuyOperationResult()
}