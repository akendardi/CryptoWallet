package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.email

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AlertDialogEditEmail(
    viewModel: ProfileAlertEmailViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    AlertDialogEditEmailContent(
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

