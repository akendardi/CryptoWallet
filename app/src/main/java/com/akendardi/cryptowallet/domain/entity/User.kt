package com.akendardi.cryptowallet.domain.entity

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val coins: List<CryptoCurrency>,
    val balance: Double
)
