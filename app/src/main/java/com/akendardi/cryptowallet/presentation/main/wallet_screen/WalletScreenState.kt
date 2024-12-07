package com.akendardi.cryptowallet.presentation.main.wallet_screen

data class WalletScreenState(
    val currentWalletScreen: CurrentWalletScreen = CurrentWalletScreen.WALLET,
    val balance: BalanceUI = BalanceUI(),
    val isFirstLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val purchasedCoins: List<PurchasedCoinUI> = listOf(),
    val isError: Boolean = false
)

enum class CurrentWalletScreen{
    WALLET,
    ADD_TO_BALANCE,
    REMOVE_FROM_BALANCE
}

data class PurchasedCoinUI(
    val symbol: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val totalPrice: String = "",
    val count: String = "",
    val percentageDifference: String = "",
    val dollarsDifference: String = "",
    val differenceType: PriceDifference = PriceDifference.NONE
)

enum class PriceDifference{
    POSITIVE, NEGATIVE, NONE
}

data class BalanceUI(
    val totalBalance: String = "",
    val freeBalance: String = "",
    val percentageDifference: String = "",
    val dollarsDifference: String = "",
    val priceDifference: PriceDifference = PriceDifference.NONE
)
