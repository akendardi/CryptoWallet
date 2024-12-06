package com.akendardi.cryptowallet.domain.entity.coin_info_user

data class UsersCoinInfo(
    val symbol: String,
    val count: Double,
    val buyingPrice: Double,
    val currentPrice: Double,
    val imageUrl: String
)
