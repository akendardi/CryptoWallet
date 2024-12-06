package com.akendardi.cryptowallet.presentation.main.wallet_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.main.wallet_screen.components.add_to_balance.AlertAddToBalance
import com.akendardi.cryptowallet.presentation.main.wallet_screen.components.remove_from_balance.AlertRemoveFromBalance
import com.akendardi.cryptowallet.presentation.main.wallet_screen.handles.HandleWalletResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(
    onCoinClick: (symbol: String, name: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WalletViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.wallet),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                },
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData = snackbarData,
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        actionColor = MaterialTheme.colorScheme.primary
                    )
                }
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        WalletScreenContent(
            state = state,
            paddingValues = paddingValues,
            onCoinClick = onCoinClick,
            onAddToBalanceClick = { viewModel.changeScreen(CurrentWalletScreen.ADD_TO_BALANCE) },
            onRemoveFromBalanceClick = { viewModel.changeScreen(CurrentWalletScreen.REMOVE_FROM_BALANCE) },
            onRefresh = viewModel::loadData
        )
        HandleWalletResult(
            snackbarHostState = snackbarHostState,
            isLoading = state.isFirstLoading,
            isError = state.isError
        )
        when (state.currentWalletScreen) {
            CurrentWalletScreen.WALLET -> {}

            CurrentWalletScreen.ADD_TO_BALANCE -> {
                AlertAddToBalance(
                    closeScreen = { viewModel.changeScreen(CurrentWalletScreen.WALLET) },
                    closeScreenAfterAdd = {
                        viewModel.changeScreen(CurrentWalletScreen.WALLET)
                        viewModel.loadData()
                    })
            }

            CurrentWalletScreen.REMOVE_FROM_BALANCE -> {
                AlertRemoveFromBalance(
                    closeScreen = { viewModel.changeScreen(CurrentWalletScreen.WALLET) },
                    closeScreenAfterAdd = {
                        viewModel.changeScreen(CurrentWalletScreen.WALLET)
                        viewModel.loadData()
                    })
            }
        }


    }


}
