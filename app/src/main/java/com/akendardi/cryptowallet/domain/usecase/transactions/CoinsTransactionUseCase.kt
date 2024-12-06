package com.akendardi.cryptowallet.domain.usecase.transactions

import com.akendardi.cryptowallet.domain.repository.CoinsTransactionsRepository
import javax.inject.Inject

class CoinsTransactionUseCase @Inject constructor(
    private val repository: CoinsTransactionsRepository
) {

    suspend fun loadCoinsTransactions(symbol: String) = repository.loadCoinsTransactions(symbol)

    fun getCoinsTransactionsResult() = repository.transactionsResult
}