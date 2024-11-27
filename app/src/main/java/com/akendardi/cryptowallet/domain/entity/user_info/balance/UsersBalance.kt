package com.akendardi.cryptowallet.domain.entity.user_info.balance

import androidx.room.Ignore
import com.akendardi.cryptowallet.domain.entity.user_info.transactions.Transaction


data class UsersBalance(
    val totalBalance: Double,
    val freeBalance: Double,
    val lockedBalance: Double,
    val purchasedCoins: List<PurchasedCoin>,
    val transactions: List<Transaction>
)