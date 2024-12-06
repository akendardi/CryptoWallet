package com.akendardi.cryptowallet.presentation.main.transactions_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.akendardi.cryptowallet.R

@Composable
fun NoTransactionsText(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.surfaceVariant,
        text = stringResource(R.string.no_transactions)
    )
}