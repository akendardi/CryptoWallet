package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.states.balance_operations.BalanceOperationResult
import kotlinx.coroutines.flow.StateFlow

interface BalanceOperationRepository {

    val currentFreeBalance: StateFlow<BalanceOperationResult>

    suspend fun resetInfo()

    suspend fun loadFreeBalance()

    suspend fun addToBalance(amount: Double)

    suspend fun removeFromBalance(amount: Double)
}