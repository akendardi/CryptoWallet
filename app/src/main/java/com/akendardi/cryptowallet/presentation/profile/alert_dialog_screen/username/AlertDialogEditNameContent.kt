package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.username

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R

@Composable
fun EditUserNameAlertDialogContent(
    onDismiss: () -> Unit,
    value: String,
    error: String,
    onValueChanged: (String) -> Unit,
    saveChangesClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        title = { Text(text = stringResource(R.string.change_username)) },
        text = {
            Column {
                Text(text = stringResource(R.string.input_new_name))
                TextField(
                    value = value,
                    onValueChange = onValueChanged,
                    supportingText = { Text(text = error) }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                saveChangesClick()
            }) {
                Text(text = stringResource(R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    )
}