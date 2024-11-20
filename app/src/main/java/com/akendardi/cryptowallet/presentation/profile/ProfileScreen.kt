package com.akendardi.cryptowallet.presentation.profile

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.presentation.profile.handles.HandleProfileResult
import com.akendardi.cryptowallet.presentation.profile.handles.HandleProfileScreen


@Composable
fun Profile(
    onButtonBackClick: () -> Unit,
    goToLogInScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.uploadProfilePhoto(it)
        }
    }

    BackHandler {
        viewModel.resetRequestAnswer()
        onButtonBackClick()

    }

    Box(modifier = modifier.fillMaxSize()) {
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
                viewModel.resetRequestAnswer()
                onButtonBackClick()
            },
            onEditPhotoClicked = {
                imagePickerLauncher.launch("image/*")
            }
        )

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter),
            snackbar = { snackbarData ->
                Snackbar(
                    snackbarData = snackbarData,
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    actionColor = MaterialTheme.colorScheme.primary
                )
            }
        )
    }

    HandleProfileResult(
        snackbarHostState = snackbarHostState,
        requestAnswer = state.requestAnswer,
        goToLogInScreen = goToLogInScreen,
        closeAnswerScreen = viewModel::closeAnswerScreen
    )

    HandleProfileScreen(
        currentScreen = state.currentScreen,
        onDismiss = viewModel::closeAlertScreen,
        isVerification = state.userInfo.isVerificationAccount
    )

}














