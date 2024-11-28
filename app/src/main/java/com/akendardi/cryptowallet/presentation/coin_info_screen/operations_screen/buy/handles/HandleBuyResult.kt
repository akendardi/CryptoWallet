package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy.handles

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.CoinInfoDetailLoading
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy.BuyOperationResult
import com.akendardi.cryptowallet.presentation.profile.utils.ShowSnackbarMessage

@Composable
fun HandleBuyResult(
    snackbarHostState: SnackbarHostState,
    result: BuyOperationResult,

) {

    val keyboardController = LocalSoftwareKeyboardController.current
    when(result){
        BuyOperationResult.Error -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = stringResource(R.string.buy_error)
            )
        }

        BuyOperationResult.Initial -> {

        }

        is BuyOperationResult.Success -> {
            keyboardController?.hide()
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = stringResource(R.string.success_buy, result.transactionId)
            )

        }

        BuyOperationResult.Loading -> {
            CoinInfoDetailLoading()
        }
    }
}