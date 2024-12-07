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
import androidx.compose.ui.unit.sp

@Composable
fun CanvasDays(
    intervals: Int,
    datesOnScreen: List<List<String>>,
    modifier: Modifier = Modifier
) {
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
    modifier: Modifier = Modifier
) {
    val primaryColorForDates = MaterialTheme.colorScheme.onBackground
    val secondaryColorForDates = MaterialTheme.colorScheme.surfaceVariant
    Canvas(
        modifier
    ) {

        drawXHours(
            intervals,
            hoursOnScreen = hoursOnScreen,
            primaryColor = primaryColorForDates,
            secondaryColor = secondaryColorForDates
        )
    }
}

fun DrawScope.drawXHours(
    intervals: Int,
    hoursOnScreen: List<List<String>>,
    primaryColor: Color,
    secondaryColor: Color
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
                    color = primaryColor.toArgb()
                    textAlign = Paint.Align.CENTER
                }
            )
            drawText(
                "$date $month",
                xPosition,
                textPadding,
                Paint().apply {
                    textSize = 8.sp.toPx()
                    color = secondaryColor.toArgb()
                    textAlign = Paint.Align.CENTER
                }
            )
        }
    }
}
