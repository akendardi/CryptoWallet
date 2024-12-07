package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.sell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.sell.handles.HandleSellResult

@Composable
fun SellCoinScreen(
    symbol: String,
    oBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val viewModel = hiltViewModel<SellCoinViewModel, SellCoinViewModel.Factory>(
        creationCallback = { factory -> factory.create(symbol) }
    )

    val state by viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        SellCoinScreenContent(
            state = state,
            onSellClick = viewModel::sellCoin,
            onValueChanged = viewModel::changeCountValue,
            onBackClick = oBackClick
        )
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .windowInsetsPadding(WindowInsets.systemBars),
            snackbar = { snackbarData ->
                Snackbar(
                    snackbarData = snackbarData,
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    actionColor = MaterialTheme.colorScheme.primary
                )
            }
        )
    }

    HandleSellResult(snackbarHostState = snackbarHostState, result = state.operationResult)


}