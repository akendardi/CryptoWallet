package com.akendardi.cryptowallet.domain.entity.user_info.balance

data class PurchasedCoin (
    val symbol: String,
    val buyPrice: Double,
    val count: Double,
    val timeBuying: Long
)