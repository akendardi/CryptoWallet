package com.akendardi.cryptowallet.presentation.main.profile

import com.akendardi.cryptowallet.domain.entity.UserInfo
import com.akendardi.cryptowallet.settings.ThemeMode

data class ProfileUiState(
    val userInfo: UserInfo = UserInfo(),
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val isNotificationsEnables: Boolean = false,
    val currentScreen: ProfileScreen = ProfileScreen.PROFILE
)

enum class ProfileScreen{
    PROFILE, EDIT_EMAIL, EDIT_NAME, EDIT_PASSWORD, VERIFICATION_EMAIL
}