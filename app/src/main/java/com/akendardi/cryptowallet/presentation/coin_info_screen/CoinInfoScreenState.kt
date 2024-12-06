package com.akendardi.cryptowallet.presentation.coin_info_screen

data class CoinInfoScreenState(
    val coinInfoState: CoinInfoState = CoinInfoState(),
    val isNotificationsEnabled: Boolean = false,
    val currentScreen: CurrentCoinInfoScreen = CurrentCoinInfoScreen.INFORMATION,
)

data class CoinInfoState(
    val symbol: String = "",
    val name: String = "",
)

enum class CurrentCoinInfoScreen {
    INFORMATION,
    TRANSACTIONS
}

