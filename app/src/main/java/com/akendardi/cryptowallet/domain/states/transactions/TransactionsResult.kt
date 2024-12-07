package com.akendardi.cryptowallet.domain.states.transactions

import com.akendardi.cryptowallet.domain.entity.user_info.transactions.Transaction

sealed class TransactionsResult {

    data object Initial: TransactionsResult()

    data object Loading: TransactionsResult()

    data object Error: TransactionsResult()

    data class Success(val transactions: List<Transaction>): TransactionsResult()
}