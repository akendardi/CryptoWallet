package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.states.transactions.TransactionsResult
import kotlinx.coroutines.flow.StateFlow

interface UsersTransactionsRepository {

    val transactionsResult: StateFlow<TransactionsResult>

    suspend fun loadUsersTransactions()
}