package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.sell.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.R

@Composable
fun BalanceCardSell(
    currentLockedBalanceForCoin: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = stringResource(R.string.coins_in_profile_price),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            Text(
                text = currentLockedBalanceForCoin,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}