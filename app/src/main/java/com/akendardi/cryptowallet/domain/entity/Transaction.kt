package com.akendardi.cryptowallet.domain.entity

data class Transaction(
    val id: Int,
    val userId: Int,
    val type: TransactionType,
    val isSuccess: Boolean
)
