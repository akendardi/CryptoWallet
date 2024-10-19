package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.email

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.EditEmailAlertDialogContent

@Composable
fun AlertDialogEditEmail(
    viewModel: ProfileAlertEmailViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    Log.d("TEST_VIEWMODEL", viewModel.toString())
    EditEmailAlertDialog(
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
fun EditEmailAlertDialog(
    onDismiss: () -> Unit,
    email: String,
    errorEmail: String,
    password: String,
    passwordError: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    sendRequestClick: () -> Unit
) {

    EditEmailAlertDialogContent(
        onDismiss = onDismiss,
        email = email,
        errorEmail = errorEmail,
        password = password,
        passwordError = passwordError,
        onEmailChanged = onEmailChanged,
        onPasswordChanged = onPasswordChanged,
        sendRequestClick = sendRequestClick,
    )
}