package com.akendardi.cryptowallet.presentation.coin_info_screen.information.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.GraphicPriceState
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.GraphicType

@Composable
fun CoinInfoGraph(
    intervals: Int,
    graphicType: GraphicType,
    graphicState: GraphicPriceState,
    onGraphTypeChange: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier
            .fillMaxWidth()
            .padding(12.dp)
            .aspectRatio(1.2f)
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
        ) {
            CanvasPrices(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxHeight(),
                intervals = intervals,
                pricesOnScreen = graphicState.pricesOnScreen
            )
            CanvasGraphic(
                modifier = Modifier
                    .weight(8.5f)
                    .fillMaxHeight(),
                prices = graphicState.prices
            )

        }

        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            GraphicTypeSwitcher(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.15f),
                graphicType = graphicType,
                onGraphTypeChange = onGraphTypeChange
            )
            when(graphicType) {
                GraphicType.HOUR -> {
                    CanvasHours(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.85f),
                        intervals = intervals,
                        hoursOnScreen = graphicState.timeStampsOnScreen
                    )
                }
                GraphicType.DAY -> {
                    CanvasDays(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.85f),
                        intervals = intervals,
                        datesOnScreen = graphicState.timeStampsOnScreen
                    )
                }
            }

        }
    }


}


