package com.akendardi.cryptowallet.presentation.coin_info_screen.information.components

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp

@Composable
fun CanvasPrices(
    intervals: Int,
    pricesOnScreen: List<String>,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
    ) {
        drawYNumbers(
            intervals,
            pricesOnScreen = pricesOnScreen
        )
    }
}


fun DrawScope.drawYNumbers(
    intervals: Int,
    pricesOnScreen: List<String>
) {
    val yStep = size.height / (intervals - 1)

    for (i in pricesOnScreen.indices) {
        val text = pricesOnScreen[i]

        val paint = Paint().apply {
            textSize = 14.sp.toPx()
            color = Color.White.toArgb()
            textAlign = Paint.Align.LEFT
        }

        val textWidth = paint.measureText(text)
        if (textWidth > size.width) {
            paint.textSize *= size.width / textWidth
        }

        drawContext.canvas.nativeCanvas.drawText(
            text,
            0f,
            yStep * i,
            paint
        )
    }
}