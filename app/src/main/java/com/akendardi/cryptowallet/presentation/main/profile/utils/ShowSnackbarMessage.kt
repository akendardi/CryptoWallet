package com.akendardi.cryptowallet.presentation.main.profile.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ShowSnackbarMessage(
    snackbarHostState: SnackbarHostState,
    message: String,
) {
    LaunchedEffect(Unit) {
        snackbarHostState.showSnackbar(
            message = message,
            actionLabel = "Понятно",
            duration = SnackbarDuration.Short
        )
    }
}