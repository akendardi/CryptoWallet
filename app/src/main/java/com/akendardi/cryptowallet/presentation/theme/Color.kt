package com.akendardi.cryptowallet.presentation.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Pink80 = Color(0xFFEFB8C8)

val Pink40 = Color(0xFFFF67C2)

val PurplePrimary = Color(0xFFc850c0)



val PositiveDifferenceColor = Color(0xFF02BA62)
val NegativeDifferenceColor = Color(0xFFED512E)

val profileGradientColor = Brush.verticalGradient(
    listOf(
        PurplePrimary,
        Pink40,
    )
)

val ShimmerColorShades = listOf(
    Color.LightGray.copy(0.9f),
    Color.LightGray.copy(0.2f),
    Color.LightGray.copy(0.9f)
)
