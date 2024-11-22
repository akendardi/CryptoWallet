package com.akendardi.cryptowallet.presentation.coin_info_screen.information.components

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.CoinInfoInformationScreenContent
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.CoinInfoInformationScreenState
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.GraphicPriceState

@Composable
fun CanvasGraphic(
    prices: List<Double>,
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colorScheme.primary
    Canvas(
        modifier
    ) {
        drawGraphic(
            data = prices,
            color = color
        )
    }
}

fun DrawScope.drawGraphic(
    data: List<Double>,
    color: Color
) {
    val maxValue = data.maxOrNull() ?: 0.0
    val minValue = data.minOrNull() ?: 0.0

    val yDifference = maxValue - minValue
    val xStep = size.width / (data.size - 1)

    val path = Path().apply {
        for (i in data.indices) {
            val y = size.height - ((data[i] - minValue) / yDifference * size.height)

            val x = i * xStep

            if (i == 0) {
                moveTo(x, y.toFloat())
            } else {
                val prevX = (i - 1) * xStep
                val prevY = (1 - data[i - 1] / maxValue) * size.height

                val controlX1 = prevX + (x - prevX) / 3
                val controlY1 = prevY.toFloat()
                val controlX2 = prevX + 2 * (x - prevX) / 3
                val controlY2 = y.toFloat()

                cubicTo(controlX1, controlY1, controlX2, controlY2, x, y.toFloat())
            }
        }
    }

    drawPath(
        path = path,
        color = color,
        style = Stroke(width = 1.dp.toPx())
    )

    val filledPath = path.apply {
        lineTo((data.size - 1) * xStep, size.height)
        lineTo(0f, size.height)
        close()
    }
    drawPath(
        path = filledPath,
        brush = Brush.verticalGradient(
            colors = listOf(
                color.copy(alpha = 0.5f),
                Color.Transparent
            ),
            startY = 0f,
            endY = size.height
        )
    )
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

    val pricesOnScreen = listOf("93,98k", "88,50k", "83,02k", "77,54k", "72,06k", "66,58k")

    CoinInfoInformationScreenContent(
        CoinInfoInformationScreenState(
            dayGraphicState = GraphicPriceState(
                timeStampsOnScreen = data,
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