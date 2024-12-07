package com.akendardi.cryptowallet.presentation.main.transactions_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.domain.entity.user_info.transactions.TransactionType
import com.akendardi.cryptowallet.presentation.main.home_screen.components.title.ImageWithShimmerEffect

@Composable
fun TransactionItem(
    transactionId: String,
    type: TransactionType,
    coinImage: String,
    amount: String,
    count: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ImageWithShimmerEffect(
                modifier = Modifier.size(58.dp),
                uri = coinImage
            )
            when (type) {
                TransactionType.Buy -> BuyCoinOperation(
                    transactionId = transactionId,
                    amount = amount,
                    count = count
                )

                TransactionType.Sell -> SellCoinOperation(
                    transactionId = transactionId,
                    amount = amount,
                    count = count
                )
            }

        }
    }
}

