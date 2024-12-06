package com.akendardi.cryptowallet.presentation.main.wallet_screen.components.add_to_balance

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.presentation.main.wallet_screen.components.BalanceOperationUiResult
import com.akendardi.cryptowallet.presentation.profile.utils.LoadingProgressBar

@Composable
fun AlertAddToBalance(
    closeScreen: () -> Unit,
    closeScreenAfterAdd: () -> Unit,
    viewModel: AlertAddToBalanceViewModel = hiltViewModel(

    )
) {
    val TAG = "AlertRemoveFromBalance"

    val state by viewModel.state.collectAsState()

    when (state.result) {
        BalanceOperationUiResult.Error -> {}

        BalanceOperationUiResult.Initial -> {
            Log.d(TAG, "AlertAddToBalance: Initial")
        }
        BalanceOperationUiResult.Loading -> {
            LoadingProgressBar()
            Log.d(TAG, "AlertAddToBalance: Loading")
        }

        BalanceOperationUiResult.LoadingBalance -> {
            Log.d(TAG, "AlertAddToBalance: LoadingBalance")
        }

        BalanceOperationUiResult.Success -> {
            Log.d(TAG, "AlertAddToBalance: Success")
            viewModel.resetInfo()
            closeScreenAfterAdd()
        }
    }

    AlertAddToBalanceContent(
        balanceOperationsState = state,
        onDismiss = {
            viewModel.resetInfo()
            closeScreen()
        },
        onButtonClick = viewModel::addToBalance,
        onValueChange = viewModel::changeCount
    )
}
