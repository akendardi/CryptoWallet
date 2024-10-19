package com.akendardi.cryptowallet.presentation.main.profile

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.email.AlertDialogEditEmail
import com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.username.AlertDialogEditName
import com.akendardi.cryptowallet.presentation.main.profile.components.ProfileInfo
import com.akendardi.cryptowallet.settings.ThemeMode


@Composable
fun Profile(
    viewModel: ProfileViewModel = hiltViewModel(),
    onButtonBackClick: () -> Unit,
    goToLogInScreen: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    Log.d("STATE_TEST", state.requestAnswer.toString())
    val snackbarHostState = remember { SnackbarHostState() }

    ProfileContent(
        name = state.userInfo.userName,
        email = state.userInfo.email,
        photoUri = state.userInfo.profileUri,
        themeMode = state.themeMode,
        isNotificationEnables = state.isNotificationsEnables,
        onThemeChanged = viewModel::changeTheme,
        onVerificationEmailClick = {
            viewModel.openSettingAlertScreen(ProfileScreen.VerificationEmail)
        },
        onEditNameClick = {
            viewModel.openSettingAlertScreen(ProfileScreen.EditName)
        },
        onEditEmailClick = {
            viewModel.openSettingAlertScreen(ProfileScreen.EditEmail)
        },
        onEditPasswordClick = {
            viewModel.openSettingAlertScreen(ProfileScreen.EditPassword)
        },
        onChangeNotificationsClick = viewModel::changeNotificationsMode,

        onButtonBackClick = {
            onButtonBackClick()
        },
    )

    HandleProfileResult(
        snackbarHostState = snackbarHostState,
        requestAnswer = state.requestAnswer,
        goToLogInScreen = goToLogInScreen,

        closeAnswerScreen = viewModel::closeAnswerScreen
    )

    when (state.currentScreen) {
        ProfileScreen.EditEmail -> {
            AlertDialogEditEmail(
                onDismiss = viewModel::closeAlertScreen
            )
        }

        ProfileScreen.EditName -> {
            AlertDialogEditName(
                onDismiss = viewModel::closeAlertScreen
            )
        }

        ProfileScreen.EditPassword -> {

        }

        ProfileScreen.Profile -> {

        }

        ProfileScreen.VerificationEmail -> {

        }
    }

}

@Composable
fun ProfileAuthErrorAlertDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text("Войти")
            }
        },
        title = {
            Text(text = "Ошибка авторизации")
        },
        text = {
            Text(text = "Ваша сессия истекла. Пожалуйста, выполните повторный вход")
        }
    )
}

@Composable
fun ProfileInternetErrorAlertDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text("Понятно")
            }
        },
        title = {
            Text(text = "Ошибка соединения")
        },
        text = {
            Text(text = "Отсутствует интернет соединение. Проверьте настройки сети и повторите попытку")
        }
    )
}


@Composable
fun HandleProfileResult(
    snackbarHostState: SnackbarHostState,
    requestAnswer: UserProfileOperationResult,
    goToLogInScreen: () -> Unit,
    closeAnswerScreen: () -> Unit

) {
    when (requestAnswer) {
        UserProfileOperationResult.AuthError -> {
            ProfileAuthErrorAlertDialog(
                onDismiss = {
                    closeAnswerScreen()
                    goToLogInScreen()
                }
            )
        }

        UserProfileOperationResult.Error -> {

        }

        UserProfileOperationResult.Initial -> {

        }

        UserProfileOperationResult.InternetError -> {
            ProfileInternetErrorAlertDialog(
                onDismiss = closeAnswerScreen
            )
        }

        UserProfileOperationResult.Loading -> {

        }

        UserProfileOperationResult.SuccessChangeEmail -> {

        }

        UserProfileOperationResult.SuccessChangeName -> {

        }

        UserProfileOperationResult.SuccessChangePassword -> {

        }

        UserProfileOperationResult.SuccessChangeProfilePhoto -> {

        }
    }
}


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
    onEditPasswordClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArrowBackButton(
            onButtonBackClick = onButtonBackClick
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


@Composable
fun SettingCard(
    themeMode: ThemeMode,
    isNotificationEnables: Boolean,
    changeThemeMode: (ThemeMode) -> Unit,
    onChangeNotificationsClick: () -> Unit,
    onVerificationEmailClick: () -> Unit,
    onEditNameClick: () -> Unit,
    onEditEmailClick: () -> Unit,
    onEditPasswordClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxSize(),
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

@Composable
fun ArrowBackButton(onButtonBackClick: () -> Unit) {
    Box(Modifier.fillMaxWidth()) {
        IconButton(onClick = {
            onButtonBackClick()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null
            )
        }
    }
}


@Composable
fun SettingsContent(
    themeMode: ThemeMode,
    isNotificationEnabled: Boolean,
    onChangeThemeClick: (ThemeMode) -> Unit,
    onVerificationEmailClick: () -> Unit,
    onChangeNotificationsClick: () -> Unit,
    onEditNameClick: () -> Unit,
    onEditEmailClick: () -> Unit,
    onEditPasswordClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
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
            SettingsItemWithSwitched(
                settingItem = SettingItem.ChangeNotifications,
                isEnabled = isNotificationEnabled,
                onCheckedChange = {
                    onChangeNotificationsClick()
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


@Composable
fun SettingsIconWithText(
    settingItem: SettingItem
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
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
    onThemeChanged: (ThemeMode) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Column {
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
    onThemeChanged: (ThemeMode) -> Unit
) {
    DropdownMenu(
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
    onThemeChanged: (ThemeMode) -> Unit
) {
    DropdownMenuItem(
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
    onCheckedChange: () -> Unit
) {
    Row(
        modifier = Modifier
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
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
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

