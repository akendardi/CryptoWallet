package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.email

import android.util.Log
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
fun AlertDialogEditEmail(
    viewModel: ProfileAlertEmailViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    Log.d("TEST_VIEWMODEL", viewModel.toString())
    EditEmailAlertDialogContent(
        onDismiss = {
            viewModel.resetInfo()
            onDismiss()
        },
        email = state.email,
        errorEmail = state.emailError,
        password = state.password,
        passwordError = state.passwordError,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        sendRequestClick = {
            viewModel.saveChange()
            onDismiss()
        },
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
) {
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