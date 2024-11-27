package com.akendardi.cryptowallet.presentation.coin_info_screen.information.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R


@Composable
fun ButtonBuy(
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
        Text(stringResource(R.string.buy), color = MaterialTheme.colorScheme.onPrimary)
    }
}