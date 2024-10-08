package com.akendardi.cryptowallet.presentation.auth.hello_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AnimatedImageAndText(onButtonClick: () -> Unit) {
    var visible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        visible = true
    }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(2000))
    ) {
        Column {
            HelloImage()
            HeadText()
            InfoText()
            ButtonGoNext(
                onButtonClick = onButtonClick
            )
        }

    }
}