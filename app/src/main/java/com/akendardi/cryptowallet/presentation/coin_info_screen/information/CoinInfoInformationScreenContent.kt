package com.akendardi.cryptowallet.presentation.coin_info_screen.information

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.components.CoinInfoGraph

const val TAG: String = "CoinInfoInformationScreenContent"


@Composable
fun CoinInfoInformationScreenContent(
    state: CoinInfoInformationScreenState,
    onGraphTypeChange: () -> Unit,
    modifier: Modifier = Modifier
) {

    Log.d(TAG, "CoinInfoInformationScreenContent: $state")

    val intervals = 6
    val graphicState = when (state.graphicType) {
        GraphicType.HOUR -> state.hourGraphicState
        GraphicType.DAY -> state.dayGraphicState
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
        )
        CoinInfoGraph(
            intervals = intervals,
            graphicType = state.graphicType,
            graphicState = graphicState,
            onGraphTypeChange = onGraphTypeChange
        )

    }
}


@Preview
@Composable
private fun ScreenPreview() {
    val data = listOf(
        listOf("20", "Окт.", "Вс"),
        listOf("26", "Окт.", "Сб"),
        listOf("1", "Нояб.", "Пт"),
        listOf("7", "Нояб.", "Чт"),
        listOf("13", "Нояб.", "Ср"),
        listOf("19", "Нояб.", "Вт")
    )


    val prices = listOf(
        67358.14, 67390.32, 66612.97, 68163.56, 66577.77, 67014.72, 67945.14, 69925.83,
        72723.67, 72331.96, 70207.54, 69473.41, 69354.01, 68747.04, 67811.95, 69378.09,
        75643.57, 75922.41, 76562.31, 76716.41, 80429.43, 88758.31, 88040.54, 90498.51,
        87334.19, 91058.92, 90630.94, 89877.16, 90514.83, 92418.48, 93492.98
    )

    val pricesOnScreen = listOf(
        "93.23k", "74.59k", "55.94k", "37.29k", "18.64k", "0.00k"
    )
    CoinInfoInformationScreenContent(
        CoinInfoInformationScreenState(
            dayGraphicState = GraphicPriceState(
                timeStampsOnScreen = data,
                pricesOnScreen = pricesOnScreen,
                prices = prices
            )
        ),
        onGraphTypeChange = {}
    )

}