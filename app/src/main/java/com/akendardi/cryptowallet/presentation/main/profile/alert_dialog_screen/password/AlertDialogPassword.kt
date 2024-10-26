package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AlertDialogEditPassword(
    viewModel: ProfileAlertPasswordViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    EditPasswordAlertDialogContent(
        onDismiss = onDismiss,
        oldPassword = state.oldPassword,
        newPassword = state.newPassword,
        newPasswordError = state.newPasswordError,
        onNewPasswordChanged = viewModel::onNewPasswordChanged,
        onOldPasswordChanged = viewModel::onOldPasswordChanged,
        sendRequestClick = {
            if (viewModel.saveChange()) {
                onDismiss()
            }
        }
    )

}

@Composable
fun EditPasswordAlertDialogContent(
    onDismiss: () -> Unit,
    oldPassword: String,
    newPassword: String,
    newPasswordError: String,
    onOldPasswordChanged: (String) -> Unit,
    onNewPasswordChanged: (String) -> Unit,
    sendRequestClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Изменить пароль") },
        text = {
            Column {
                Text(text = "Введите старый пароль")
                TextField(
                    value = oldPassword,
                    onValueChange = onOldPasswordChanged,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )
                Text(text = "Введите новый пароль")
                TextField(
                    value = newPassword,
                    onValueChange = onNewPasswordChanged,
                    supportingText = { Text(text = newPasswordError) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                sendRequestClick()
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