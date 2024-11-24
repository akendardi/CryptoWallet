package com.akendardi.cryptowallet.presentation.coin_info_screen.information.components.graphic

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.CoinInfoInformationScreenContent
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.CoinInfoInformationScreenState
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.GraphicPriceState
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.GraphicType

@Composable
fun CanvasDays(
    intervals: Int,
    datesOnScreen: List<List<String>>,
    modifier: Modifier = Modifier) {
    val primaryColorForDates = MaterialTheme.colorScheme.onBackground
    val secondaryColorForDates = MaterialTheme.colorScheme.surfaceVariant
    Canvas(
        modifier
    ) {

        drawXDates(
            intervals,
            datesOnScreen = datesOnScreen,
            primaryColor = primaryColorForDates,
            secondaryColor = secondaryColorForDates
        )
    }
}

fun DrawScope.drawXDates(
    intervals: Int,
    datesOnScreen: List<List<String>>,
    primaryColor: Color,
    secondaryColor: Color
) {
    val xStep = size.width / intervals


    for (i in datesOnScreen.indices) {
        val date = datesOnScreen[i][0]
        val dayOfWeek = datesOnScreen[i][2]

        val xPosition = xStep * (i + 1) - xStep / 2
        val textPadding = size.height / 3
        drawContext.canvas.nativeCanvas.apply {
            drawText(
                date,
                xPosition,
                textPadding * 2,
                Paint().apply {
                    textSize = 14.sp.toPx()
                    color = primaryColor.toArgb()
                    textAlign = Paint.Align.CENTER
                }
            )
            drawText(
                dayOfWeek,
                xPosition,
                textPadding,
                Paint().apply {
                    textSize = 12.sp.toPx()
                    color = secondaryColor.toArgb()
                    textAlign = Paint.Align.CENTER
                }
            )
        }
    }
}

@Composable
fun CanvasHours(
    intervals: Int,
    hoursOnScreen: List<List<String>>,
    modifier: Modifier = Modifier) {
    Canvas(
        modifier
    ) {

        drawXHours(
            intervals,
            hoursOnScreen = hoursOnScreen
        )
    }
}

fun DrawScope.drawXHours(
    intervals: Int,
    hoursOnScreen: List<List<String>>
) {
    val xStep = size.width / intervals


    for (i in hoursOnScreen.indices) {
        val time = hoursOnScreen[i][0]
        val date = hoursOnScreen[i][1]
        val month = hoursOnScreen[i][2]

        val xPosition = xStep * (i + 1) - xStep / 2
        val textPadding = size.height / 3
        drawContext.canvas.nativeCanvas.apply {
            drawText(
                time,
                xPosition,
                textPadding * 2,
                Paint().apply {
                    textSize = 14.sp.toPx()
                    color = Color.White.toArgb()
                    textAlign = Paint.Align.CENTER
                }
            )
            drawText(
                "$date $month",
                xPosition,
                textPadding,
                Paint().apply {
                    textSize = 8.sp.toPx()
                    color = Color.Gray.toArgb()
                    textAlign = Paint.Align.CENTER
                }
            )
        }
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
    val timeData = listOf(
        listOf("20", "19", "Нояб."),
        listOf("00", "20", "Нояб."),
        listOf("04", "", ""),
        listOf("08", "", ""),
        listOf("12", "", ""),
        listOf("19", "", "")
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
            graphicType = GraphicType.HOUR,
            dayGraphicState = GraphicPriceState(
                timeStampsOnScreen = data,
                pricesOnScreen = pricesOnScreen,
                prices = prices
            ),
            hourGraphicState = GraphicPriceState(
                timeStampsOnScreen = timeData,
                pricesOnScreen = pricesOnScreen,
                prices = prices
            )
        ),
        onGraphTypeChange = {},
        onRefresh = {},
        onBuyClick = {},
        onSellClick = {}
    )

}
