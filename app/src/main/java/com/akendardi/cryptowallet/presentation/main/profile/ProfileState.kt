package com.akendardi.cryptowallet.presentation.main.profile

import com.akendardi.cryptowallet.domain.entity.UserInfo
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import com.akendardi.cryptowallet.settings.ThemeMode

data class ProfileUiState(
    val userInfo: UserInfo = UserInfo(),
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val isNotificationsEnables: Boolean = false,
    val currentScreen: ProfileScreen = ProfileScreen.Profile,
    val isOpenAnswerScreen: Boolean = false,
    val requestAnswer: UserProfileOperationResult = UserProfileOperationResult.Initial
)


sealed class ProfileScreen {

    data object Profile : ProfileScreen()

    data object EditEmail : ProfileScreen()

    data object EditName : ProfileScreen()

    data object EditPassword : ProfileScreen()

    data object VerificationEmail : ProfileScreen()

}