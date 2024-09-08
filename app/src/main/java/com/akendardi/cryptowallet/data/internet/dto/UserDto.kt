package com.akendardi.cryptowallet.data.internet.dto

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val coins: Unit,
    val balance: Double
)