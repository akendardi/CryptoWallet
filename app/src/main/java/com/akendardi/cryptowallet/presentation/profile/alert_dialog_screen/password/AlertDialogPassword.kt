package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.password

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AlertDialogEditPassword(
    viewModel: ProfileAlertPasswordViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    AlertDialogEditPasswordContent(
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

