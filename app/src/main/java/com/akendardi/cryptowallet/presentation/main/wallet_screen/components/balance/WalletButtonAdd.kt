package com.akendardi.cryptowallet.presentation.main.wallet_screen.components.balance

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign


@Composable
fun WalletButtonAdd(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Пополнить",
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onPrimary
            ),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}