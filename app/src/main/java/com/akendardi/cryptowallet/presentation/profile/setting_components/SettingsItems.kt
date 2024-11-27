package com.akendardi.cryptowallet.presentation.profile.setting_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.settings.ThemeMode

@Composable
fun SettingsIconWithText(
    settingItem: SettingItem,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            modifier = modifier.size(36.dp),
            imageVector = settingItem.icon,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null
        )
        Text(
            text = settingItem.text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun SettingsItemWithDropDownMenu(
    currentTheme: ThemeMode,
    onThemeChanged: (ThemeMode) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable { expanded = true },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SettingsIconWithText(
                settingItem = SettingItem.ChangeTheme
            )
            IconButton(
                onClick = {
                    expanded = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }
        }
        ThemeDropDownMenu(
            expanded = expanded,
            selectedTheme = currentTheme,
            closeMenu = {
                expanded = false
            },
            onThemeChanged = onThemeChanged
        )
    }

}

@Composable
fun ThemeDropDownMenu(
    expanded: Boolean,
    selectedTheme: ThemeMode,
    closeMenu: () -> Unit,
    onThemeChanged: (ThemeMode) -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        modifier = modifier,
        offset = DpOffset(200.dp, 0.dp),
        expanded = expanded,
        onDismissRequest = {
            closeMenu()
        },
    ) {
        ThemeDropDownMenuItem(
            text = if (selectedTheme == ThemeMode.SYSTEM) stringResource(R.string.theme_auto_enabled) else stringResource(
                R.string.theme_auto
            ),
            onThemeChanged = {
                onThemeChanged(ThemeMode.SYSTEM)
            }
        )
        ThemeDropDownMenuItem(
            text = if (selectedTheme == ThemeMode.LIGHT) stringResource(R.string.theme_light_enabled) else stringResource(
                R.string.theme_light
            ),
            onThemeChanged = {
                onThemeChanged(ThemeMode.LIGHT)
            }
        )
        ThemeDropDownMenuItem(
            text = if (selectedTheme == ThemeMode.DARK) stringResource(R.string.theme_dark_enabled) else stringResource(
                R.string.theme_dark
            ),
            onThemeChanged = {
                onThemeChanged(ThemeMode.DARK)
            }
        )
    }
}

@Composable
fun ThemeDropDownMenuItem(
    text: String,
    onThemeChanged: (ThemeMode) -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItem(
        modifier = modifier,
        text = {
            Text(text)
        },
        onClick = {
            onThemeChanged(ThemeMode.DARK)
        }
    )
}

@Composable
fun SettingsItemWithSwitched(
    settingItem: SettingItem,
    isEnabled: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SettingsIconWithText(
            settingItem = settingItem
        )
        Switch(checked = isEnabled, onCheckedChange = {
            onCheckedChange()
        })
    }
}

@Composable
fun SettingsItemWithArrow(
    settingItem: SettingItem,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SettingsIconWithText(
            settingItem = settingItem
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null
        )
    }
}