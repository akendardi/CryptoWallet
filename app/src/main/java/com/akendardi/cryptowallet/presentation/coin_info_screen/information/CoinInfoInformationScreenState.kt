package com.akendardi.cryptowallet.presentation.coin_info_screen.information

import com.akendardi.cryptowallet.presentation.coin_info_screen.CoinInfoState

data class CoinInfoInformationScreenState(
    val isFirstLoading: Boolean = false,
    val coinInfoState: CoinInfoState = CoinInfoState(),
    val graphicType: GraphicType = GraphicType.DAY,
    val hourGraphicState: GraphicPriceState = GraphicPriceState(),
    val dayGraphicState: GraphicPriceState = GraphicPriceState()
)

data class GraphicPriceState(
    val timeStampsOnScreen: List<List<String>> = emptyList(),
    val pricesOnScreen: List<String> = emptyList(),
    val prices: List<Double> = emptyList()
)


enum class GraphicType {
    HOUR,
    DAY
}