package com.akendardi.cryptowallet.presentation.main.profile.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R

@Composable
fun ShowSnackbarMessage(
    snackbarHostState: SnackbarHostState,
    message: String,
    actionLabel: String = stringResource(R.string.understand)
) {
    LaunchedEffect(Unit) {
        snackbarHostState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = SnackbarDuration.Short
        )
    }
}