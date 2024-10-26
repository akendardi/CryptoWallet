package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.errors

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ProfileAuthErrorAlertDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text("Войти")
            }
        },
        title = {
            Text(text = "Ошибка авторизации")
        },
        text = {
            Text(text = "Ваша сессия истекла. Пожалуйста, выполните повторный вход")
        }
    )
}