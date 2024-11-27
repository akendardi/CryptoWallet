package com.akendardi.cryptowallet.domain.entity.user_info.transactions

sealed class TransactionType {

    data class Buy(
        val userId: String,
        val symbol: String,
        val price: Double,
        val count: Double,
        val amount: Double,
        val time: Long
    ) : TransactionType()

    data class Sell(
        val userId: String,
        val symbol: String,
        val price: Double,
        val count: Double,
        val time: Long
    ) : TransactionType()

    data class Sending(
        val symbol: String,
        val senderId: String,
        val receiverId: String,
        val price: Double,
        val count: Double,
        val time: Long
    )
}