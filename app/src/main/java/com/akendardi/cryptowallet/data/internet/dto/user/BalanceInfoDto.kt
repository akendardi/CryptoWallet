package com.akendardi.cryptowallet.data.internet.dto.user

import com.akendardi.cryptowallet.domain.entity.user_info.transactions.TransactionType

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
    val count: Double? = null
)

data class TransactionDto(
    val transactionId: Int = 0,
    val type: String = "",
    val userId: String = "",
    val symbol: String = "",
    val price: Double = 0.0,
    val count: Double = 0.0,
    val amount: Double = 0.0,
    val time: Long = 0L
)
