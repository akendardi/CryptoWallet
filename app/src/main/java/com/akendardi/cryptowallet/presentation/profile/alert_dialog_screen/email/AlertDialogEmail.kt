package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.email

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.R

@Composable
fun AlertDialogEditEmail(
    viewModel: ProfileAlertEmailViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.state.collectAsState()

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
            if (viewModel.saveChange()) {
                onDismiss()
            }
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
    sendRequestClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        title = { Text(text = stringResource(R.string.change_email)) },
        text = {
            Column {
                Text(text = stringResource(R.string.input_new_email))
                TextField(
                    value = email,
                    onValueChange = onEmailChanged,
                    supportingText = { Text(text = errorEmail) }
                )
                Text(text = stringResource(R.string.input_password))
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
                Text(stringResource(R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}