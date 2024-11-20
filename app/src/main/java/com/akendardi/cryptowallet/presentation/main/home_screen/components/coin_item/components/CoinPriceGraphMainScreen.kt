package com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun CoinPriceGraphMainScreen(
    dataPoints: List<Double>,
    color: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxHeight()) {
        if (dataPoints.isEmpty()) return@Canvas

        val minY = dataPoints.minOrNull() ?: 0.0
        val maxY = dataPoints.maxOrNull() ?: 1.0
        val yRange = maxY - minY

        val normalizedDataPoints = dataPoints.map { size.height - ((it - minY) / yRange * size.height) }
        val spacing = size.width / (dataPoints.size - 1)

        val path = Path().apply {
            moveTo(0f, normalizedDataPoints[0].toFloat())

            for (i in 0 until normalizedDataPoints.size - 1) {
                val startX = i * spacing
                val startY = normalizedDataPoints[i].toFloat()
                val endX = (i + 1) * spacing
                val endY = normalizedDataPoints[i + 1].toFloat()

                val controlX1 = startX + spacing / 3
                val controlX2 = endX - spacing / 3

                cubicTo(controlX1, startY, controlX2, endY, endX, endY)
            }
        }

        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 1.dp.toPx())
        )
    }

}