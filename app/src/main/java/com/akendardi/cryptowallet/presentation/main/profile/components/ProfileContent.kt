package com.akendardi.cryptowallet.presentation.main.profile.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.akendardi.cryptowallet.presentation.main.profile.setting_components.SettingCard
import com.akendardi.cryptowallet.settings.ThemeMode

@Composable
fun ProfileContent(
    onButtonBackClick: () -> Unit,
    name: String,
    photoUri: Uri,
    email: String,
    themeMode: ThemeMode,
    isNotificationEnables: Boolean,
    onThemeChanged: (ThemeMode) -> Unit,
    onVerificationEmailClick: () -> Unit,
    onChangeNotificationsClick: () -> Unit,
    onEditNameClick: () -> Unit,
    onEditEmailClick: () -> Unit,
    onEditPasswordClick: () -> Unit,
    onEditPhotoClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopButtons(
            onButtonBackClick = onButtonBackClick,
            onEditPhotoClicked = onEditPhotoClicked
        )

        ProfileInfo(
            name = name,
            photoUri = photoUri,
            email = email
        )

        SettingCard(
            themeMode = themeMode,
            isNotificationEnables = isNotificationEnables,
            changeThemeMode = onThemeChanged,
            onEditNameClick = onEditNameClick,
            onEditEmailClick = onEditEmailClick,
            onEditPasswordClick = onEditPasswordClick,
            onChangeNotificationsClick = onChangeNotificationsClick,
            onVerificationEmailClick = onVerificationEmailClick
        )
    }
}