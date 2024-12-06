package com.akendardi.cryptowallet.domain.entity.user_info.balance

data class PurchasedCoin (
    val symbol: String,
    val name: String,
    val buyPrice: Double,
    val currentPrice: Double,
    val count: Double,
    val imageUrl: String
)

