package com.akendardi.cryptowallet.presentation.main.transactions_screen

import com.akendardi.cryptowallet.presentation.main.transactions_screen.components.TransactionUi

data class UsersTransactionsScreenState(
    val isFirstLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val transactions: List<TransactionUi> = listOf(),
    val isError: Boolean = false
)
