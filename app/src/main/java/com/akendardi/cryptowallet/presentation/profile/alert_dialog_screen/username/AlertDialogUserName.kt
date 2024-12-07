package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.username

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

