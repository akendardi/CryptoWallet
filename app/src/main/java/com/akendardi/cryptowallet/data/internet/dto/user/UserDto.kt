package com.akendardi.cryptowallet.data.internet.dto.user

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val coins: List<CoinDto>,
    val balance: Double,
    val balanceHistory: List<Double>
)