package com.akendardi.cryptowallet.presentation.coin_info_screen

data class CoinInfoScreenState(
    val coinInfoState: CoinInfoState = CoinInfoState(),
    val isNotificationsEnabled: Boolean = true,
    val hourGraphicState: GraphicPriceState = GraphicPriceState(),
    val dayGraphicState: GraphicPriceState = GraphicPriceState()
)

data class CoinInfoState(
    val symbol: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val price: Double = 0.0,
)

data class GraphicPriceState(
    val datesOnScreen: List<List<String>> = emptyList(),
    val prices: List<Double> = emptyList()
)