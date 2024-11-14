package com.akendardi.cryptowallet.domain.entity

data class DetailCoinInfo(
    val symbol: String,
    val name: String,
    val imageUrl: String,
    val prices: List<Double>
)
