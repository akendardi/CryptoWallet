package com.akendardi.cryptowallet.presentation.coin_info_screen.transactions

import com.akendardi.cryptowallet.presentation.main.transactions_screen.components.TransactionUi

data class CoinsInfoTransactionsScreenState(
    val isFirstLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val transactions: List<TransactionUi> = listOf(),
    val isError: Boolean = false
)
