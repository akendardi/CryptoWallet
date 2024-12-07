package com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item

import androidx.compose.runtime.Composable
import com.akendardi.cryptowallet.domain.entity.coin_info_search.CoinInfoSearch

@Composable
fun SearchCoinItem(
    coinInfoSearch: CoinInfoSearch,
    onItemClicked: (symbol: String, name: String) -> Unit,
) {

    SearchCoinItemContent(
        coinInfoSearch = coinInfoSearch,
        onItemClicked = onItemClicked
    )

}