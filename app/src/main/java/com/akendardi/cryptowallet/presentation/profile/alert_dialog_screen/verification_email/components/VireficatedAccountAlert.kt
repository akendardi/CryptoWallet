package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.verification_email.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.R

@Composable
fun VerificatedEmailAlert(onDismiss: () -> Unit, modifier: Modifier = Modifier) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        title = { Text(text = stringResource(R.string.verification_email)) },
        text = {
            Column {
                Text(text = stringResource(R.string.verification_email_description))
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )
                Text(
                    text = stringResource(R.string.email_verificated),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        confirmButton = {}
    )
}