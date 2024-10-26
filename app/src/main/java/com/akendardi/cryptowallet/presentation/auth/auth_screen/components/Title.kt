package com.akendardi.cryptowallet.presentation.auth.auth_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun Title(
    modifier: Modifier = Modifier
) {

    var isCryptoAnimated by remember { mutableStateOf(false) }
    var isWalletAnimated by remember { mutableStateOf(false) }

    LaunchedEffect(isCryptoAnimated) {
        isCryptoAnimated = true
        delay(200)
        isWalletAnimated = true
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {

        AnimatedVisibility(
            visible = isCryptoAnimated,
            enter = slideInHorizontally() + expandHorizontally(expandFrom = Alignment.End) + fadeIn()
        ) {
            Text(
                text = "Crypto",
                textAlign = TextAlign.Start,
                fontSize = 60.sp,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .padding(
                        PaddingValues(
                            top = 16.dp,
                            start = 60.dp
                        )
                    ),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        AnimatedVisibility(
            visible = isWalletAnimated,
            enter = slideInHorizontally { fullWidth -> fullWidth } + fadeIn()
        ) {
            Text(
                text = "wallet",
                textAlign = TextAlign.End,
                fontSize = 60.sp,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        end = 60.dp
                    ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }


}