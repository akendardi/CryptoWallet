package com.akendardi.cryptowallet.presentation.main.profile.handles

import androidx.compose.runtime.Composable
import com.akendardi.cryptowallet.presentation.main.profile.ProfileScreen
import com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.email.AlertDialogEditEmail
import com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.password.AlertDialogEditPassword
import com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.username.AlertDialogEditName
import com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.verification_email.AlertDialogVerificateEmail

@Composable
fun HandleProfileScreen(
    currentScreen: ProfileScreen,
    onDismiss: () -> Unit,
    isVerification: Boolean
) {
    when (currentScreen) {
        ProfileScreen.EditEmail -> {
            AlertDialogEditEmail(
                onDismiss = onDismiss
            )
        }

        ProfileScreen.EditName -> {
            AlertDialogEditName(
                onDismiss = onDismiss
            )
        }

        ProfileScreen.EditPassword -> {
            AlertDialogEditPassword(
                onDismiss = onDismiss
            )
        }

        ProfileScreen.Profile -> {

        }

        ProfileScreen.VerificationEmail -> {
            AlertDialogVerificateEmail(
                onDismiss = onDismiss,
                isVerification = isVerification
            )
        }
    }
}