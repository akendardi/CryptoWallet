package com.akendardi.cryptowallet.presentation.main.wallet_screen.components.add_to_balance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.components.CoinsOperationsTextField
import com.akendardi.cryptowallet.presentation.main.wallet_screen.components.BalanceOperationsState

@Composable
fun AlertAddToBalanceContent(
    balanceOperationsState: BalanceOperationsState,
    onDismiss: () -> Unit,
    onButtonClick: () -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    ) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = onDismiss,
            title = { Text(text = stringResource(R.string.replenishment)) },
            text = {
                Column {
                    Text(text = stringResource(R.string.replenishment_sum))
                    CoinsOperationsTextField(
                        isEnabled = true,
                        amount = balanceOperationsState.amount,
                        onValueChanged = onValueChange,
                        error = balanceOperationsState.error
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = onButtonClick) {
                    Text(text = stringResource(R.string.add_to_balance))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        )
    }

}