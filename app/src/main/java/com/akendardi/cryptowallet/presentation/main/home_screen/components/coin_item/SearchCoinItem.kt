package com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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


@Preview
@Composable
private fun SearchCoinInfoPreview() {
    SearchCoinItem(
        coinInfoSearch = CoinInfoSearch(
            name = "Ethereum",
            symbol = "BTC",
            imageUrl = Uri.parse("https://s2.coinmarketcap.com/static/img/coins/64x64/1.png")
        )
    ) {_, _ ->}
}