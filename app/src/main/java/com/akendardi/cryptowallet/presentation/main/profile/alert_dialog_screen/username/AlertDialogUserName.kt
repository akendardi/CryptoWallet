package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.username

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.R

@Composable
fun AlertDialogEditName(
    viewModel: ProfileAlertUsernameViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    Log.d("TEST_VIEWMODEL", viewModel.toString())
    EditUserNameAlertDialogContent(
        value = state.name,
        error = state.error,
        onValueChanged = viewModel::onUserNameChanged,
        saveChangesClick = {
            if (viewModel.saveChange()) {
                onDismiss()
            }
        },
        onDismiss = {
            viewModel.resetInfo()
            onDismiss()
        }
    )
}

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
                Text(text = stringResource(R.string.understand))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    )
}