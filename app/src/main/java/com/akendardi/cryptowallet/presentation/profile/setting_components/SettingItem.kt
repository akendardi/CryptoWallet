package com.akendardi.cryptowallet.presentation.profile.setting_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import com.akendardi.cryptowallet.presentation.icons.Shield_lock

sealed class SettingItem(
    val icon: ImageVector,
    val text: String
) {

    data object VerificationEmail : SettingItem(
        icon = Icons.Default.Check,
        text = "Подтверждение почты"
    )

    data object ChangeTheme : SettingItem(
        icon = Icons.Filled.DarkMode,
        text = "Темная тема"
    )

    data object ChangeNotifications : SettingItem(
        icon = Icons.Filled.Notifications,
        text = "Вкл/Выкл уведомления"
    )

    data object ChangePassword : SettingItem(
        icon = Shield_lock,
        text = "Изменить пароль"
    )

    data object ChangeName : SettingItem(
        icon = Icons.Default.Edit,
        text = "Имя профиля"
    )

    data object ChangeEmail : SettingItem(
        icon = Icons.Default.MailOutline,
        text = "Email"
    )
}