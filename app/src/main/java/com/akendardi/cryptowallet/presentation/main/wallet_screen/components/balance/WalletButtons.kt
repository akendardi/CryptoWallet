package com.akendardi.cryptowallet.presentation.main.wallet_screen.components.balance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WalletButtons(
    onAddToBalanceClick: () -> Unit,
    onRemoveFromBalanceClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 15.dp,
                end = 15.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        WalletButtonAdd(
            onClick = onAddToBalanceClick,
            modifier = Modifier.weight(1f)
        )
        WalletButtonRemove(
            onClick = onRemoveFromBalanceClick,
            modifier = Modifier.weight(1f)
        )


    }
}