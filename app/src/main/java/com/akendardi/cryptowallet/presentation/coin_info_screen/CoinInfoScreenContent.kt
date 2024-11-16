package com.akendardi.cryptowallet.presentation.coin_info_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.akendardi.cryptowallet.presentation.coin_info_screen.components.title.TitleCoinInfo

@Composable
fun CoinInfoScreenContent(
    state: CoinInfoScreenState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier.fillMaxSize(),
        topBar = {
            TitleCoinInfo(
                coinInfoState = state.coinInfoState,
                isNotificationsEnabled = state.isNotificationsEnabled,
                modifier = Modifier.statusBarsPadding()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

        }

    }
}