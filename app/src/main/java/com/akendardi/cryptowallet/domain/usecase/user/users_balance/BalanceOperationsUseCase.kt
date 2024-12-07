package com.akendardi.cryptowallet.domain.usecase.user.users_balance

import com.akendardi.cryptowallet.domain.repository.BalanceOperationRepository
import javax.inject.Inject

class BalanceOperationsUseCase @Inject constructor(
    private val repository: BalanceOperationRepository
) {
    fun getBalanceOperationsResult() = repository.currentFreeBalance

    suspend fun loadBalance() = repository.loadFreeBalance()

    suspend fun resetInfo() = repository.resetInfo()

    suspend fun addToBalance(amount: Double) = repository.addToBalance(amount)

    suspend fun removeFromBalance(amount: Double) = repository.removeFromBalance(amount)
}