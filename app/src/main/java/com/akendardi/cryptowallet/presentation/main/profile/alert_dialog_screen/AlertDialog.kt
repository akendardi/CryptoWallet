package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.akendardi.cryptowallet.presentation.auth.auth_screen.FieldType


@Composable
fun CustomAlertDialog(
    fieldType: FieldType,
    onDismiss: () -> Unit,
    value: String,
    error: String,
    onValueChanged: (String) -> Unit,
    saveChangesClick: () -> Unit
) {
    val label = when (fieldType) {
        FieldType.USERNAME -> "Изменить имя"
        FieldType.EMAIL -> "Изменить Email"
        FieldType.PASSWORD -> "Изменить пароль"
    }
    val text = when(fieldType){
        FieldType.USERNAME -> "Введите новое имя:"
        FieldType.EMAIL -> "Введите новый Email:"
        FieldType.PASSWORD -> "Введите новый пароль:"
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = label) },
        text = {
            Column {
                Text(text = text)
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

@Composable
fun EditEmailAlertDialogContent(
    onDismiss: () -> Unit,
    email: String,
    errorEmail: String,
    password: String,
    passwordError: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    sendRequestClick: () -> Unit
){
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Изменить Email") },
        text = {
            Column {
                Text(text = "Введите новую почту")
                TextField(
                    value = email,
                    onValueChange = onEmailChanged,
                    supportingText = { Text(text = errorEmail) }
                )
                Text(text = "Введите пароль")
                TextField(
                    value = password,
                    onValueChange = onPasswordChanged,
                    supportingText = { Text(text = passwordError) },
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
