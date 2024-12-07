package com.akendardi.cryptowallet.presentation.coin_info_screen.information.components.graphic

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

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