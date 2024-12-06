package com.akendardi.cryptowallet.presentation.main.wallet_screen.components.remove_from_balance

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.presentation.main.wallet_screen.components.BalanceOperationUiResult
import com.akendardi.cryptowallet.presentation.profile.utils.LoadingProgressBar

@Composable
fun AlertRemoveFromBalance(
    closeScreen: () -> Unit,
    closeScreenAfterAdd: () -> Unit,
    viewModel: AlertRemoveFromBalanceViewModel = hiltViewModel(
    )
) {
    val TAG = "AlertRemoveFromBalance"

    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadBalance()
    }

    when (state.result) {
        BalanceOperationUiResult.Error -> {}

        BalanceOperationUiResult.Initial -> {
            Log.d(TAG, "AlertRemoveFromBalance: Initial")
        }
        BalanceOperationUiResult.Loading -> {
            Log.d(TAG, "AlertRemoveFromBalance: Loading")
            LoadingProgressBar()
        }

        BalanceOperationUiResult.LoadingBalance -> {
            Log.d(TAG, "AlertRemoveFromBalance: LoadingBalance")
        }

        BalanceOperationUiResult.Success -> {
            Log.d(TAG, "AlertRemoveFromBalance: Success")
            viewModel.resetInfo()
            closeScreenAfterAdd()
        }
    }

    AlertRemoveFromBalanceContent(
        balanceOperationsState = state,
        onDismiss = {
            viewModel.resetInfo()
            closeScreen()
        },
        onButtonClick = viewModel::removeFromBalance,
        onValueChange = viewModel::changeCount
    )
}
