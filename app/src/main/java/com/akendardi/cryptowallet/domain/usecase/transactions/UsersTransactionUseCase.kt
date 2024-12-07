package com.akendardi.cryptowallet.domain.usecase.transactions

import com.akendardi.cryptowallet.domain.repository.UsersTransactionsRepository
import javax.inject.Inject

class UsersTransactionUseCase @Inject constructor(
    private val repository: UsersTransactionsRepository
) {
    suspend fun loadUsersTransactions() = repository.loadUsersTransactions()

    fun getUserTransactions() = repository.transactionsResult
}