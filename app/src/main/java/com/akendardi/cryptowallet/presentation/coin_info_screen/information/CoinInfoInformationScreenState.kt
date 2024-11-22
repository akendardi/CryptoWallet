package com.akendardi.cryptowallet.presentation.coin_info_screen.information

data class CoinInfoInformationScreenState(
    val isLoading: Boolean = false,
    val coinInfoState: CoinInfoDetailState = CoinInfoDetailState(),
    val graphicType: GraphicType = GraphicType.DAY,
    val hourGraphicState: GraphicPriceState = GraphicPriceState(),
    val dayGraphicState: GraphicPriceState = GraphicPriceState()
)

data class GraphicPriceState(
    val timeStampsOnScreen: List<List<String>> = emptyList(),
    val pricesOnScreen: List<String> = emptyList(),
    val prices: List<Double> = emptyList()
)

data class CoinInfoDetailState(
    val symbol: String = "",
    val name: String = "",
    val price: String = "",
    val lastDateUpdate: String = ""
)


enum class GraphicType {
    HOUR,
    DAY
}