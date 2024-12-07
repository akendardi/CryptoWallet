package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy.handles

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.BoxLoading
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.components.OperationResult
import com.akendardi.cryptowallet.presentation.profile.utils.ShowSnackbarMessage

@Composable
fun HandleBuyResult(
    snackbarHostState: SnackbarHostState,
    result: OperationResult,

    ) {

    val keyboardController = LocalSoftwareKeyboardController.current
    when (result) {
        OperationResult.Error -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = stringResource(R.string.buy_error)
            )
        }

        OperationResult.Initial -> {

        }

        is OperationResult.Success -> {
            keyboardController?.hide()
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = stringResource(R.string.success_buy, result.transactionId)
            )

        }

        OperationResult.Loading -> {
            BoxLoading()
        }
    }
}