package com.akendardi.cryptowallet.presentation.main.transactions_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.main.transactions_screen.components.NoTransactionsText
import com.akendardi.cryptowallet.presentation.main.transactions_screen.components.TransactionItem
import com.akendardi.cryptowallet.presentation.main.transactions_screen.components.TransactionUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreenContent(
    transactions: List<TransactionUi>,
    isRefreshing: Boolean,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,

    ) {
    val pullRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        state = pullRefreshState,
        onRefresh = onRefresh,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding()
            )
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                top = 8.dp,
                start = 12.dp,
                end = 12.dp,
                bottom = 8.dp
            )
        ) {
            if (transactions.isEmpty() && !isLoading) {
                item {
                    NoTransactionsText()
                }

            }
            items(transactions) {
                TransactionItem(
                    transactionId = it.transactionId,
                    type = it.type,
                    coinImage = it.coinImage,
                    amount = it.amount,
                    count = it.count
                )
            }
        }
    }
}