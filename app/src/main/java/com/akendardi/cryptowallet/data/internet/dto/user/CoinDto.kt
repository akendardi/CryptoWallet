package com.akendardi.cryptowallet.data.internet.dto.user

data class CoinDto(
    val id: Int,
    val name: String,
    val shortName: String,
    val amount: Double,
    val price: Double
)