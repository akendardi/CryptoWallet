package com.akendardi.cryptowallet.presentation.coin_info_screen.information.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonsOperations(
    onBuyClick: () -> Unit,
    onSellClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ButtonBuy(
            onClick = onBuyClick,
            modifier = Modifier.weight(1f)
        )
        ButtonSell(
            onClick = onSellClick,
            modifier = Modifier.weight(1f)
        )
    }
}