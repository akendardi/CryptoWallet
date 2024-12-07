package com.akendardi.cryptowallet.presentation.main.wallet_screen.components.add_to_balance

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

    val state by viewModel.state.collectAsState()

    when (state.result) {
        BalanceOperationUiResult.Error -> {}

        BalanceOperationUiResult.Initial -> {
        }

        BalanceOperationUiResult.Loading -> {
            LoadingProgressBar()
        }

        BalanceOperationUiResult.LoadingBalance -> {
        }

        BalanceOperationUiResult.Success -> {
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
