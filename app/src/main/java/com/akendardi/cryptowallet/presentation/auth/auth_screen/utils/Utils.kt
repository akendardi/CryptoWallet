package com.akendardi.cryptowallet.presentation.auth.auth_screen.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.akendardi.cryptowallet.domain.states.auth.AuthResult
import com.akendardi.cryptowallet.presentation.auth.auth_screen.AuthType

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
            .clickable(enabled = false) { },
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun ShowSnackbarAuthMessage(
    snackbarHostState: SnackbarHostState,
    message: String,
    onAuthTypeChanged: (AuthType) -> Unit
) {
    LaunchedEffect(Unit) {
        onAuthTypeChanged(AuthType.SIGN_IN)
        snackbarHostState.showSnackbar(
            message = message,
            actionLabel = "Понятно",
            duration = SnackbarDuration.Short
        )
    }
}

@Composable
fun HandleAuthResult(
    authType: AuthType,
    authResult: AuthResult,
    snackbarHostState: SnackbarHostState,
    goToMainScreen: () -> Unit,
    goToHelloScreen: () -> Unit,
    changeAuthType: (AuthType) -> Unit
) {
    when (authResult) {
        is AuthResult.Error -> {
            ShowSnackbarAuthMessage(
                snackbarHostState,
                message = authResult.e,
                onAuthTypeChanged = { changeAuthType(AuthType.SIGN_IN) }
            )
        }

        AuthResult.Loading -> {
            Loading()
        }

        AuthResult.Success -> {
            LaunchedEffect(Unit) {
                if (authType == AuthType.SIGN_IN) {
                    goToMainScreen()
                } else {
                    goToHelloScreen()
                }
            }
        }

        AuthResult.SuccessSentLink -> {
            ShowSnackbarAuthMessage(
                snackbarHostState = snackbarHostState,
                message = "Сообщение отправлено на вашу почту",
                onAuthTypeChanged = { changeAuthType(AuthType.SIGN_IN) }
            )
        }

        else -> Unit
    }
}