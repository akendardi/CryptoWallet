package com.akendardi.cryptowallet.domain.entity.coin_info_detail

data class CoinInfoDetail(
    val symbol: String,
    val name: String,
    val imageUrl: String,
    val currentPrice: Double,
    val lastUpdate: Long,
    val pricesHour: List<PricePoint>,
    val pricesDay: List<PricePoint>
)


