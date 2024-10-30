package com.akendardi.cryptowallet.presentation.main.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.main.home_screen.components.CoinItem
import com.akendardi.cryptowallet.presentation.main.home_screen.components.CoinsTitle

@Composable
fun HomeScreenContent(
    state: HomeScreenUIState,
    onProfileClickListener: () -> Unit,
    logout: () -> Unit,
    onSearchButtonClick: () -> Unit,
    loadNextPage: () -> Unit,
    modifier: Modifier = Modifier
) {

    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {
            TitleTextHomeScreen(
                userInfoState = state.userInfoState,
                onProfileClickListener = onProfileClickListener,
                logout = logout
            )
        }

        item {
            UserBalanceInfo()
        }

        item {
            CoinsTitle(onSearchButtonClick = onSearchButtonClick)
        }

        state.coinsListState.coinsList.forEach { coinInfo ->
            item {
                CoinItem(coinInfo = coinInfo)
            }
        }

        if (state.coinsListState.isLoading) {
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
        if (state.coinsListState.coinsList.isNotEmpty()){
            item {
                LaunchedEffect(Unit) {
                    loadNextPage()
                }
            }
        }


    }
}
