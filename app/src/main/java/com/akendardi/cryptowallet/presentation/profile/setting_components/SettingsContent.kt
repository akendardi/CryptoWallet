package com.akendardi.cryptowallet.presentation.profile.setting_components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.settings.ThemeMode


@Composable
fun SettingsContent(
    themeMode: ThemeMode,
    onChangeThemeClick: (ThemeMode) -> Unit,
    onVerificationEmailClick: () -> Unit,
    onEditNameClick: () -> Unit,
    onEditEmailClick: () -> Unit,
    onEditPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 5.dp,
                bottom = 5.dp
            ),
    ) {
        item {
            SettingsItemWithDropDownMenu(
                onThemeChanged = {
                    onChangeThemeClick(it)
                },
                currentTheme = themeMode
            )
        }
        item {
            SettingsItemWithArrow(
                settingItem = SettingItem.VerificationEmail,
                onItemClick = {
                    onVerificationEmailClick()
                }
            )
        }
        item {
            SettingsItemWithArrow(
                settingItem = SettingItem.ChangeName,
                onItemClick = {
                    onEditNameClick()
                }
            )
        }
        item {
            SettingsItemWithArrow(
                settingItem = SettingItem.ChangeEmail,
                onItemClick = {
                    onEditEmailClick()
                }
            )
        }
        item {
            SettingsItemWithArrow(
                settingItem = SettingItem.ChangePassword,
                onItemClick = {
                    onEditPasswordClick()
                }
            )
        }
    }


}


