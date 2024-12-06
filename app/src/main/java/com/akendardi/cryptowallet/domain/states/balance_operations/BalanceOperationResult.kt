package com.akendardi.cryptowallet.domain.states.balance_operations

sealed class BalanceOperationResult {

    data object Initial: BalanceOperationResult()

    data object LoadingBalance: BalanceOperationResult()

    data class BalanceLoaded(val freeBalance: Double): BalanceOperationResult()

    data object LoadingOperation: BalanceOperationResult()

    data object Success: BalanceOperationResult()

    data object Error: BalanceOperationResult()
}