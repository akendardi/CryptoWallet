package com.akendardi.cryptowallet.presentation.profile.setting_components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.settings.ThemeMode

@Composable
fun SettingCard(
    themeMode: ThemeMode,
    isNotificationEnables: Boolean,
    changeThemeMode: (ThemeMode) -> Unit,
    onChangeNotificationsClick: () -> Unit,
    onVerificationEmailClick: () -> Unit,
    onEditNameClick: () -> Unit,
    onEditEmailClick: () -> Unit,
    onEditPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        SettingsContent(
            themeMode = themeMode,
            isNotificationEnabled = isNotificationEnables,
            onChangeThemeClick = {
                changeThemeMode(it)
            },
            onChangeNotificationsClick = onChangeNotificationsClick,
            onVerificationEmailClick = onVerificationEmailClick,
            onEditNameClick = onEditNameClick,
            onEditEmailClick = onEditEmailClick,
            onEditPasswordClick = onEditPasswordClick
        )
    }
}