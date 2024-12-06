package com.akendardi.cryptowallet.presentation.profile

import android.net.Uri
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import com.akendardi.cryptowallet.settings.ThemeMode

data class ProfileUiState(
    val generalUserInfo: ProfileUserState = ProfileUserState(),
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val isNotificationsEnables: Boolean = false,
    val currentScreen: ProfileScreen = ProfileScreen.Profile,
    val isOpenAnswerScreen: Boolean = false,
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val requestAnswer: UserProfileOperationResult = UserProfileOperationResult.Initial,
    val error: String = ""
)

data class ProfileUserState(
    val userName: String = "",
    val profileUri: Uri = Uri.EMPTY,
    val email: String = "",
    val isVerificatedAccount: Boolean = false
)


sealed class ProfileScreen {

    data object Profile : ProfileScreen()

    data object EditEmail : ProfileScreen()

    data object EditName : ProfileScreen()

    data object EditPassword : ProfileScreen()

    data object VerificationEmail : ProfileScreen()

}