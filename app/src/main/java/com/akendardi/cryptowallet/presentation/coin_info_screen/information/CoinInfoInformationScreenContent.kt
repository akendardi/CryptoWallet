package com.akendardi.cryptowallet.presentation.coin_info_screen.information

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.components.CoinInfoGraph
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.components.CoinInfoInformationTitle

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
            CoinInfoDetailLoading()
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
                    ButtonsBuyAndSell(
                        onBuyClick = onBuyClick,
                        onSellClick = onSellClick
                    )
                }
            }
        }
    }

}

@Composable
fun ButtonsBuyAndSell(
    onBuyClick: () -> Unit,
    onSellClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ButtonBuy(
            onClick = onBuyClick,
            modifier = Modifier.weight(1f)
        )
        ButtonSell(
            onClick = onSellClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ButtonBuy(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = onClick
    ) {
        Text(stringResource(R.string.buy), color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun ButtonSell(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = onClick
    ) {
        Text(
            stringResource(R.string.sell),
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}


@Composable
fun CoinInfoDetailLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Preview
@Composable
private fun ScreenPreview() {
    val data = listOf(
        listOf("20", "Окт.", "Вс"),
        listOf("26", "Окт.", "Сб"),
        listOf("1", "Нояб.", "Пт"),
        listOf("7", "Нояб.", "Чт"),
        listOf("13", "Нояб.", "Ср"),
        listOf("19", "Нояб.", "Вт")
    )


    val prices = listOf(
        67358.14, 67390.32, 66612.97, 68163.56, 66577.77, 67014.72, 67945.14, 69925.83,
        72723.67, 72331.96, 70207.54, 69473.41, 69354.01, 68747.04, 67811.95, 69378.09,
        75643.57, 75922.41, 76562.31, 76716.41, 80429.43, 88758.31, 88040.54, 90498.51,
        87334.19, 91058.92, 90630.94, 89877.16, 90514.83, 92418.48, 93492.98
    )

    val pricesOnScreen = listOf(
        "93.23k", "74.59k", "55.94k", "37.29k", "18.64k", "0.00k"
    )
    CoinInfoInformationScreenContent(
        CoinInfoInformationScreenState(
            dayGraphicState = GraphicPriceState(
                timeStampsOnScreen = data,
                pricesOnScreen = pricesOnScreen,
                prices = prices
            )
        ),
        onGraphTypeChange = {},
        onRefresh = {},
        onBuyClick = {},
        onSellClick = {}
    )

}