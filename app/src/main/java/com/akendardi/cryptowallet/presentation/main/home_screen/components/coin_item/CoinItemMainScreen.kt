package com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item

import androidx.compose.runtime.Composable
import com.akendardi.cryptowallet.domain.entity.coin_info_general.CoinInfoGeneral


@Composable
fun CoinItemMainScreen(
    coinInfoGeneral: CoinInfoGeneral,
    onItemClicked: (symbol: String, name: String) -> Unit
) {
    CoinItemContentMainScreen(
        coinInfoGeneral = coinInfoGeneral,
        onItemClicked = onItemClicked
    )
}
