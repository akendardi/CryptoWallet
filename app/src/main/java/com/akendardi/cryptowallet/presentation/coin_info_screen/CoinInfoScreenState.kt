package com.akendardi.cryptowallet.presentation.coin_info_screen

data class CoinInfoScreenState(
    val coinInfoState: CoinInfoState = CoinInfoState(),
    val isNotificationsEnabled: Boolean = false,
    val currentScreen: CurrentCoinInfoScreen = CurrentCoinInfoScreen.INFORMATION,
    val currentOperationScreen: OperationCoinInfoScreen = OperationCoinInfoScreen.NONE
)

data class CoinInfoState(
    val symbol: String = "",
    val name: String = "",
)

enum class OperationCoinInfoScreen{
    NONE,
    BUY,
    SELL
}

enum class CurrentCoinInfoScreen {
    INFORMATION,
    TRANSACTIONS
}

