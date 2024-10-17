package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.username

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.presentation.auth.auth_screen.FieldType
import com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.CustomAlertDialog

@Composable
fun AlertDialogEditName(
    viewModel: ProfileAlertUsernameViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    Log.d("TEST_VIEWMODEL", viewModel.toString())
    AlertDialogEditNameContent(
        value = state.name,
        error = state.error,
        onValueChanged = viewModel::onUserNameChanged,
        saveChangesClick = {
            if(viewModel.saveChange()){
                onDismiss()
            }
        },
        onDismiss = {
            viewModel.resetInfo()
            onDismiss()
        }
    )
}

@Composable
fun AlertDialogEditNameContent(
    value: String,
    error: String,
    onValueChanged: (String) -> Unit,
    onDismiss: () -> Unit,
    saveChangesClick: () -> Unit
) {
    CustomAlertDialog(
        fieldType = FieldType.USERNAME,
        onDismiss = onDismiss,
        value = value,
        error = error,
        onValueChanged = onValueChanged,
        saveChangesClick = saveChangesClick
    )
}