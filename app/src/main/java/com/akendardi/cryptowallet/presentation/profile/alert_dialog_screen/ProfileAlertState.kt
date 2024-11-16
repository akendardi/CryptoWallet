package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen

data class ProfileAlertState(
    val name: String = "",
    val email: String = "",
    val alertPasswordState: AlertPasswordState = AlertPasswordState()
)

data class AlertPasswordState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val newPasswordRepeated: String = ""
)