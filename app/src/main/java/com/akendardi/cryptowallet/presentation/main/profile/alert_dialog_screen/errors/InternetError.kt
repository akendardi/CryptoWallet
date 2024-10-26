package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.errors

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable




@Composable
fun ProfileInternetErrorAlertDialog(
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
                Text("Понятно")
            }
        },
        title = {
            Text(text = "Ошибка соединения")
        },
        text = {
            Text(text = "Отсутствует интернет соединение. Проверьте настройки сети и повторите попытку")
        }
    )
}