package com.akendardi.cryptowallet.presentation.main.wallet_screen

import android.util.Log
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
import com.akendardi.cryptowallet.presentation.main.wallet_screen.components.NoSellsText
import com.akendardi.cryptowallet.presentation.main.wallet_screen.components.WalletsCoinItem
import com.akendardi.cryptowallet.presentation.main.wallet_screen.components.balance.WalletBalanceInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreenContent(
    state: WalletScreenState,
    paddingValues: PaddingValues,
    onAddToBalanceClick: () -> Unit,
    onRemoveFromBalanceClick: () -> Unit,
    onCoinClick: (symbol: String, name: String) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pullRefreshState = rememberPullToRefreshState()

    Log.d("WALLET_TEST", "WalletScreenContent: $state")

    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
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
            if (!state.isFirstLoading) {
                item {
                    WalletBalanceInfo(
                        balanceUI = state.balance,
                        onAddToBalanceClick = onAddToBalanceClick,
                        onRemoveFromBalanceClick = onRemoveFromBalanceClick
                    )
                }
            }
            if (state.purchasedCoins.isEmpty() && !state.isFirstLoading) {
                item {
                    NoSellsText()
                }
            }
            items(state.purchasedCoins) { coin ->
                WalletsCoinItem(coin = coin, onCoinClick = onCoinClick)
            }
        }
    }
}