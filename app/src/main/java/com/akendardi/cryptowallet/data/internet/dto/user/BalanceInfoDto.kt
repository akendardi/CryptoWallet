package com.akendardi.cryptowallet.data.internet.dto.user

data class BalanceInfoDto(
    val freeBalance: Double = 0.0,
    val purchasedCoins: List<PurchasedCoinDto> = listOf(),
    val transactions: List<TransactionDto> = listOf()
)



