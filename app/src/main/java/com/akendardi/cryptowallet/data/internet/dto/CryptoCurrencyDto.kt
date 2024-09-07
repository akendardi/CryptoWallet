package com.akendardi.cryptowallet.data.internet.dto

data class CryptoCurrencyDto(
    val id: Int,
    val name: String,
    val symbol: String,
    val currentPriceUSD: Int
)
