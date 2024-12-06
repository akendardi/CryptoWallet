package com.akendardi.cryptowallet.presentation.main.transactions_screen.components

import com.akendardi.cryptowallet.domain.entity.user_info.transactions.TransactionType

data class TransactionUi(
    val transactionId: String,
    val type: TransactionType,
    val coinImage: String,
    val amount: String,
    val count: String
)
