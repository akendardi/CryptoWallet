package com.akendardi.cryptowallet.presentation.auth.auth_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.presentation.auth.auth_screen.components.AuthScreenContent
import com.akendardi.cryptowallet.presentation.auth.auth_screen.utils.HandleAuthResult

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    goToMainScreen: () -> Unit,
    goToHelloScreen: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        )
        AuthScreenContent(
            authType = state.authType,
            textFieldsErrorsState = state.textFieldsErrorsState,
            textFieldsState = state.textFieldsState,
            onAuthTypeChanged = viewModel::changeAuthType,
            onEmailChanged = viewModel::onEmail,
            onNameChanged = viewModel::onUsername,
            onPasswordChanged = viewModel::onPassword,
            onButtonClick = {
                viewModel.sendAuthRequest()
                keyboardController?.hide()
            },
            onTextFieldIconClick = {
                viewModel.actionTextField(it)
            }
        )

        HandleAuthResult(
            authType = state.authType,
            authResult = state.authResult,
            snackbarHostState = snackbarHostState,
            goToMainScreen = {
                goToMainScreen()
            },
            goToHelloScreen = {
                goToHelloScreen()
            },
            changeAuthType = viewModel::changeAuthType
        )
    }
}







