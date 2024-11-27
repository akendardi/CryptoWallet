package com.akendardi.cryptowallet.presentation.coin_info_screen.components.title

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun CoinPriceCoinInfoScreen(
    price: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = price,
        textAlign = TextAlign.End,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        style = MaterialTheme.typography.titleMedium
    )
}