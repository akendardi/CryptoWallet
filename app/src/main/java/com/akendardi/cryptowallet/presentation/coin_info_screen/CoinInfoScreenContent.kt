package com.akendardi.cryptowallet.presentation.coin_info_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.akendardi.cryptowallet.presentation.coin_info_screen.components.tabs.CoinInfoTabs
import com.akendardi.cryptowallet.presentation.coin_info_screen.components.title.TitleCoinInfo
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.CoinInfoInformationScreen
import com.akendardi.cryptowallet.presentation.coin_info_screen.transactions.CoinInfoTransactionsScreen

@Composable
fun CoinInfoScreenContent(
    state: CoinInfoScreenState,
    onTabSelected: (Int) -> Unit,
    onBackButtonClick: () -> Unit,
    onBuyClick: () -> Unit,
    onSellClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier.fillMaxSize(),
        topBar = {
            TitleCoinInfo(
                coinInfoState = state.coinInfoState,
                isNotificationsEnabled = state.isNotificationsEnabled,
                onBackButtonClick = onBackButtonClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CoinInfoTabs(
                selectedTab = state.currentScreen,
                onTabSelected = onTabSelected
            )

            when (state.currentScreen) {
                CurrentCoinInfoScreen.INFORMATION -> CoinInfoInformationScreen(
                    symbol = state.coinInfoState.symbol,
                    onBuyClick = {},
                    onSellClick = {}
                )
                CurrentCoinInfoScreen.TRANSACTIONS -> CoinInfoTransactionsScreen(
                    symbol = state.coinInfoState.symbol
                )
            }
        }

    }
}