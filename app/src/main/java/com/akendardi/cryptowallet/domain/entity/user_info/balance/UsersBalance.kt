package com.akendardi.cryptowallet.domain.entity.user_info.balance


data class UsersBalance(
    val freeBalance: Double,
    val purchasedCoins: List<PurchasedCoin>
)