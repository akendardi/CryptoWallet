package com.akendardi.cryptowallet.presentation.auth.hello_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.akendardi.cryptowallet.presentation.auth.auth_screen.components.Title
import com.akendardi.cryptowallet.presentation.auth.hello_screen.components.AnimatedImageAndText


@Composable
fun HelloScreen(
    onButtonClick: () -> Unit
) {
    HelloScreenContent(
        onButtonClick = onButtonClick
    )
}

@Composable
fun HelloScreenContent(onButtonClick: () -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Title()
            AnimatedImageAndText(onButtonClick = onButtonClick)
        }

    }
}









