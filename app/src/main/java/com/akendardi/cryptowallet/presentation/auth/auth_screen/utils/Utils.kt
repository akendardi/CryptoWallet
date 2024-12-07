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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.domain.states.auth.AuthResult
import com.akendardi.cryptowallet.presentation.auth.auth_screen.AuthType

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
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
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        onAuthTypeChanged(AuthType.SIGN_IN)
        snackbarHostState.showSnackbar(
            message = message,
            actionLabel = context.getString(R.string.understand),
            duration = SnackbarDuration.Short
        )
    }
}

@Composable
fun HandleAuthResult(
    authResult: AuthResult,
    snackbarHostState: SnackbarHostState,
    goToMainScreen: () -> Unit,
    goToHelloScreen: () -> Unit,
    changeAuthType: (AuthType) -> Unit
) {
    when (authResult) {
        AuthResult.Initial -> {

        }
        AuthResult.SuccessCreatedAccount -> {
            LaunchedEffect(Unit) {
                goToHelloScreen()
            }
        }

        AuthResult.SuccessLogin -> {
            LaunchedEffect(Unit) {
                goToMainScreen()
            }
        }

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


        AuthResult.SuccessSentLink -> {
            ShowSnackbarAuthMessage(
                snackbarHostState = snackbarHostState,
                message = stringResource(R.string.message_sent),
                onAuthTypeChanged = { changeAuthType(AuthType.SIGN_IN) }
            )
        }
    }
}