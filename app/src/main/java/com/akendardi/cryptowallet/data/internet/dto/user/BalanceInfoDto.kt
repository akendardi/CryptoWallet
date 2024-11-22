package com.akendardi.cryptowallet.data.internet.dto.user

data class BalanceInfoDto(
    val totalBalance: Double? = null,
    val freeBalance: Double? = null,
    val lockedBalance: Double? = null,
    val purchasedCoins: List<PurchasedCoinDto>? = null,
    val transactions: List<TransactionDto>? = null
)

data class PurchasedCoinDto(
    val symbol: String? = null,
    val buyPrice: Double? = null,
    val count: Double? = null,
    val timeBuying: Long? = null
)

data class TransactionDto(
    val transactionId: Int? = null,
    val symbol: String? = null,
    val count: Double? = null,
    val timeBuying: Long? = null,
    val transactionType: String? = null
)
