package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R


@Composable
fun BuyingButton(
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12),
        onClick = onClick,
        enabled = isEnabled
    ) {
        Text(
            text = stringResource(R.string.confirm_purchase),
            style = MaterialTheme.typography.titleLarge
        )
    }
}