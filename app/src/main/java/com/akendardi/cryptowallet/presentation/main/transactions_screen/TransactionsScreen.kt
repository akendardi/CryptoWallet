package com.akendardi.cryptowallet.presentation.main.transactions_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.akendardi.cryptowallet.presentation.main.transactions_screen.handles.HandleTransactionsResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    viewModel: TransactionsScreenViewModel = hiltViewModel(),

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
                        text = stringResource(R.string.transactions),
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
        TransactionsScreenContent(
            transactions = state.transactions,
            isRefreshing = state.isRefreshing,
            onRefresh = viewModel::loadData,
            isLoading = state.isFirstLoading,
            paddingValues = paddingValues
        )
        HandleTransactionsResult(
            snackbarHostState = snackbarHostState,
            isLoading = state.isFirstLoading,
            isError = state.isError
        )

    }


}