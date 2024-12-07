package com.akendardi.cryptowallet.presentation.main.wallet_screen.handles

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.BoxLoading
import com.akendardi.cryptowallet.presentation.profile.utils.ShowSnackbarMessage

@Composable
fun HandleWalletResult(
    snackbarHostState: SnackbarHostState,
    isLoading: Boolean,
    isError: Boolean
) {
    if (isLoading) {
        BoxLoading()
    }

    if (isError) {
        ShowSnackbarMessage(
            snackbarHostState = snackbarHostState,
            message = stringResource(id = R.string.something_went_wrong)
        )
    }
}