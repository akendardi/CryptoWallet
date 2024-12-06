package com.akendardi.cryptowallet.domain.entity.user_info.transactions

data class Transaction(
    val transactionId: Int,
    val type: TransactionType,
    val userId: String,
    val symbol: String,
    val coinImage: String,
    val price: Double,
    val count: Double,
    val amount: Double,
    val time: Long
)

sealed class TransactionType {

    data object Buy : TransactionType()

    data object Sell : TransactionType()

}
