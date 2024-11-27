package com.akendardi.cryptowallet.presentation.theme

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush


@Composable
fun rememberShimmerBrush(): Brush {
    val transition = rememberInfiniteTransition(label = "")
    val shimmerTranslateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    return Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(shimmerTranslateAnim.value, shimmerTranslateAnim.value),
        end = Offset(shimmerTranslateAnim.value + 300f, shimmerTranslateAnim.value + 300f)
    )
}