package com.akendardi.cryptowallet.domain.entity

data class CoinInfoDetail(
    val symbol: String,
    val name: String,
    val imageUrl: String,
    val currentPrice: Double,
    val pricesHour: List<PricePoint>,
    val pricesDay: List<PricePoint>
)

data class PricePoint(
    val time: Long,
    val price: Double
)
