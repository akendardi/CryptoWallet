package com.akendardi.cryptowallet.presentation.profile

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.akendardi.cryptowallet.presentation.profile.components.ProfileInfo
import com.akendardi.cryptowallet.presentation.profile.components.TopButtons
import com.akendardi.cryptowallet.presentation.profile.setting_components.SettingCard
import com.akendardi.cryptowallet.settings.ThemeMode

@Composable
fun ProfileContent(
    onButtonBackClick: () -> Unit,
    name: String,
    photoUri: Uri,
    email: String,
    themeMode: ThemeMode,
    onThemeChanged: (ThemeMode) -> Unit,
    onVerificationEmailClick: () -> Unit,
    onEditNameClick: () -> Unit,
    onEditEmailClick: () -> Unit,
    onEditPasswordClick: () -> Unit,
    onEditPhotoClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
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
                changeThemeMode = onThemeChanged,
                onEditNameClick = onEditNameClick,
                onEditEmailClick = onEditEmailClick,
                onEditPasswordClick = onEditPasswordClick,
                onVerificationEmailClick = onVerificationEmailClick
            )
        }
    }

}