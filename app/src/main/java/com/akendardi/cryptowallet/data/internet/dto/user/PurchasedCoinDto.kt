package com.akendardi.cryptowallet.data.internet.dto.user

data class PurchasedCoinDto(
    val symbol: String = "",
    val name: String = "",
    val buyPrice: Double = 0.0,
    val count: Double = 0.0,
    val imageUrl: String = ""
)