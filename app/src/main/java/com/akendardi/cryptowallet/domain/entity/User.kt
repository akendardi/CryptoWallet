package com.akendardi.cryptowallet.domain.entity

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val coins: List<CryptoCoin>,
    val balance: Double,
    val balanceHistory: List<Double> = listOf(0.0),
    val transactions: List<Transaction> = listOf()
)
