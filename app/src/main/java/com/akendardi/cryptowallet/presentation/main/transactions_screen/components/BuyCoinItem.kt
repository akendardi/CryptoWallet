package com.akendardi.cryptowallet.presentation.main.transactions_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.theme.NegativeDifferenceColor

@Composable
fun BuyCoinOperation(
    transactionId: String,
    amount: String,
    count: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(R.string.buy_with_count, count),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surfaceVariant,
                text = stringResource(R.string.transaction_with_number, transactionId),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            textAlign = TextAlign.End,
            text = amount,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            color = NegativeDifferenceColor,
        )
    }
}