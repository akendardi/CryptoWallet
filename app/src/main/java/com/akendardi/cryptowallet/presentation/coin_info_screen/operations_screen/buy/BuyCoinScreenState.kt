package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy

data class BuyCoinScreenState(
    val isLoading: Boolean = true,
    val name: String = "",
    val symbol: String = "",
    val imageUrl: String = "",
    val currentPrice: String = "0.0$",
    val amount: String = "",
    val currentFreeBalance: String = "0.0$",
    val totalCount: String = "0.0$",
    val error: String = ""
)