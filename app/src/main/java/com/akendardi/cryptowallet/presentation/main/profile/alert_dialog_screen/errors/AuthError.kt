package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.errors

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R

@Composable
fun ProfileAuthErrorAlertDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(text = stringResource(R.string.sign_in))
            }
        },
        title = {
            Text(text = stringResource(R.string.auth_error))
        },
        text = {
            Text(text = stringResource(R.string.session_canceled))
        }
    )
}