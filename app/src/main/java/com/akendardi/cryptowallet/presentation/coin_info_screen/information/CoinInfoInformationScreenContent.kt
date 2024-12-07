package com.akendardi.cryptowallet.presentation.coin_info_screen.information

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.components.CoinInfoInformationTitle
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.components.buttons.ButtonsOperations
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.components.graphic.CoinInfoGraph

const val TAG: String = "CoinInfoInformationScreenContent"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinInfoInformationScreenContent(
    state: CoinInfoInformationScreenState,
    onGraphTypeChange: () -> Unit,
    onRefresh: () -> Unit,
    onBuyClick: () -> Unit,
    onSellClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val intervals = 6
    val graphicState = when (state.graphicType) {
        GraphicType.HOUR -> state.hourGraphicState
        GraphicType.DAY -> state.dayGraphicState
    }

    Log.d(TAG, "CoinInfoInformationScreenContent: $state")
    val refreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        modifier = modifier.fillMaxSize(),
        state = refreshState,
        isRefreshing = (state.isLoading && state.coinInfoState.lastDateUpdate != ""),
        onRefresh = {
            Log.d(TAG, "CoinInfoInformationScreenContent: $state")
            onRefresh()
        }
    ) {
        if (state.isLoading && state.coinInfoState.lastDateUpdate == "") {
            BoxLoading()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                item {
                    CoinInfoInformationTitle(
                        coinInfoDetailState = state.coinInfoState
                    )
                }
                item {
                    CoinInfoGraph(
                        intervals = intervals,
                        graphicType = state.graphicType,
                        graphicState = graphicState,
                        onGraphTypeChange = onGraphTypeChange
                    )
                }
                item {
                    ButtonsOperations(
                        onBuyClick = onBuyClick,
                        onSellClick = onSellClick
                    )
                }
            }
        }
    }

}


@Composable
fun BoxLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}