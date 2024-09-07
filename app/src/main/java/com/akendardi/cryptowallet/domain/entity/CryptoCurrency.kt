package com.akendardi.cryptowallet.domain.entity

data class CryptoCurrency(
    val id: Int,
    val name: String,
    val symbol: String,
    val currentPriceUSD: Int
)
