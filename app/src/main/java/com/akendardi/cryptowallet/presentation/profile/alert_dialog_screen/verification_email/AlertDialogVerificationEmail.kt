package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.verification_email

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.R

@Composable
fun AlertDialogVerificateEmail(
    onDismiss: () -> Unit,
    isVerification: Boolean,
    viewModel: ProfileAlertVerificationEmailViewModel = hiltViewModel()
) {
    AlertDialogVerificateEmailContent(
        onDismiss = onDismiss,
        isVerification = isVerification,
        onButtonClick = {
            viewModel.verificateEmail()
            onDismiss()
        }
    )
}




