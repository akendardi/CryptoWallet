package com.akendardi.cryptowallet.presentation.main.wallet_screen.components

data class BalanceOperationsState(
    val isLoading: Boolean = false,
    val currentBalance: String = "",
    val amount: String = "",
    val error: String = "",
    val result: BalanceOperationUiResult = BalanceOperationUiResult.Initial
)

sealed class BalanceOperationUiResult() {
    data object Initial : BalanceOperationUiResult()

    data object Loading : BalanceOperationUiResult()

    data object Success : BalanceOperationUiResult()

    data object LoadingBalance: BalanceOperationUiResult()

    data object Error : BalanceOperationUiResult()
}