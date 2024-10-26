package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.verification_email

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AlertDialogVerificateEmail(
    onDismiss: () -> Unit,
    isVerification: Boolean,
    viewModel: ProfileAlertVerificationEmailViewModel = hiltViewModel()
) {
    VerificationEmailAlertDialogContent(
        onDismiss = onDismiss,
        isVerification = isVerification,
        onButtonClick = {
            viewModel.verificateEmail()
            onDismiss()
        }
    )
}

@Composable
fun VerificationEmailAlertDialogContent(
    onDismiss: () -> Unit,
    isVerification: Boolean,
    onButtonClick: () -> Unit
) {

    when (isVerification) {
        true -> {
            VerificationEmailAlert(
                onDismiss = onDismiss
            )
        }


        false -> {
            UnVerificationEmailAlert(
                onDismiss = onDismiss,
                onButtonClick = onButtonClick
            )
        }


    }


}

@Composable
fun VerificationEmailAlert(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Подтверждение Email") },
        text = {
            Column {
                Text(text = "Подтвержденый Email позволяет вам торговать на рынке.")
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp))
                Text(text = "Ваш Email подтвержден!", style = MaterialTheme.typography.titleMedium)
            }
        },
        confirmButton = {}
    )
}

@Composable
fun UnVerificationEmailAlert(
    onDismiss: () -> Unit,
    onButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Подтверждение Email") },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Подтвержденый Email позволяет вам торговать на рынке.")
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp))
                Text(
                    text = "Ваш Email не подтвержден!",
                    style = MaterialTheme.typography.titleMedium
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    onClick = onButtonClick
                ) {
                    Text(text = "Подтвердить Email")
                }
            }
        },
        confirmButton = {}
    )
}