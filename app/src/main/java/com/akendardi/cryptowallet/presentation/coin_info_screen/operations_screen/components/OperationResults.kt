package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.akendardi.cryptowallet.R


@Composable
fun OperationResults(
    totalCount: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.total),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "$$totalCount",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }

}