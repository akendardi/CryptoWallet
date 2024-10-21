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
import androidx.hilt.navigation.compose.hiltViewModel

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
    saveChangesClick: () -> Unit
) {

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Изменить имя") },
        text = {
            Column {
                Text(text = "Введите новое имя")
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
                Text("Сохранить")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Отмена")
            }
        }
    )
}