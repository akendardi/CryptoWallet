package com.akendardi.cryptowallet.presentation.main.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.main.home_screen.components.CoinsTitle
import com.akendardi.cryptowallet.presentation.main.home_screen.components.balance.UserBalanceInfo
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.CoinItemMainScreen
import com.akendardi.cryptowallet.presentation.main.home_screen.components.shimmer_effects.ShimmerAnimationCoinItem
import com.akendardi.cryptowallet.presentation.main.home_screen.components.title.TitleHomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    state: HomeScreenUIState,
    onProfileClickListener: () -> Unit,
    logout: () -> Unit,
    onSearchButtonClick: () -> Unit,
    loadNextPage: () -> Unit,
    onItemClicked: (symbol: String, name: String) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    val pullRefreshState = rememberPullToRefreshState()



    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
        state = pullRefreshState,
        onRefresh = onRefresh,
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 12.dp,
                start = 12.dp,
                end = 12.dp
            )
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            userScrollEnabled = !state.coinsListState.isFirstLoading
        ) {
            item {
                TitleHomeScreen(
                    userInfoState = state.userInfoState,
                    onProfileClickListener = onProfileClickListener,
                    logout = logout
                )
            }

            item {
                UserBalanceInfo(
                    totalBalance = state.userBalanceState.totalBalance,
                    differencePercent = state.userBalanceState.differencePercentage,
                    onDepositButtonClick = onSearchButtonClick
                )
            }

            item {
                CoinsTitle(onSearchButtonClick = onSearchButtonClick)
            }

            if (state.coinsListState.isFirstLoading) {
                items(10) {
                    ShimmerAnimationCoinItem()
                }
            }

            state.coinsListState.coinsList.forEach { coinInfo ->
                item {
                    CoinItemMainScreen(
                        coinInfoGeneral = coinInfo,
                        onItemClicked = onItemClicked
                    )
                }
            }

            if (state.coinsListState.isLoadingNextPage) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            if (state.coinsListState.coinsList.isNotEmpty()) {
                item {
                    LaunchedEffect(Unit) {
                        loadNextPage()
                    }
                }
            }
        }
    }

}

