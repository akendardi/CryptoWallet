package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.password

data class ProfileAlertPasswordState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val newPasswordError: String = ""
)
