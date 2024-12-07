package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.components

sealed class OperationResult{

    data object Initial: OperationResult()

    data object Loading: OperationResult()

    data object Error: OperationResult()

    data class Success(val transactionId: Int): OperationResult()
}