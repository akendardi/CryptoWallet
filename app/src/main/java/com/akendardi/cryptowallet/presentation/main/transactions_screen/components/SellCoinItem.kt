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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.akendardi.cryptowallet.presentation.theme.PositiveDifferenceColor


@Composable
fun SellCoinOperation(
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
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = "Продажа $count",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surfaceVariant,
                text = "Транзакция: $transactionId",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            textAlign = TextAlign.End,
            maxLines = 1,
            text = amount,
            style = MaterialTheme.typography.titleMedium,
            color = PositiveDifferenceColor,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SellCoinOperation(transactionId = "1", amount = "2000 0000", count = "0.00043")
}