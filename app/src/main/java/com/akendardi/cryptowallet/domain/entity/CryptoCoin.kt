package com.akendardi.cryptowallet.domain.entity

data class CryptoCoin(
    val id: Int,
    val name: String,
    val symbol: String,
    val amount: Double,
    val price: Double,
    val imageUrl: String
)
