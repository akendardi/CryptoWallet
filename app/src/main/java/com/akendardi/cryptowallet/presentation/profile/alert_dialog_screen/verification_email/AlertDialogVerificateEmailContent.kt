package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.verification_email

import androidx.compose.runtime.Composable
import com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.verification_email.components.UnVerificatedEmailAlert
import com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.verification_email.components.VerificatedEmailAlert

@Composable
fun AlertDialogVerificateEmailContent(
    onDismiss: () -> Unit,
    isVerification: Boolean,
    onButtonClick: () -> Unit
) {

    when (isVerification) {
        true -> {
            VerificatedEmailAlert(
                onDismiss = onDismiss
            )
        }


        false -> {
            UnVerificatedEmailAlert(
                onDismiss = onDismiss,
                onButtonClick = onButtonClick
            )
        }


    }


}