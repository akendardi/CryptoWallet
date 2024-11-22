package com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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

@Preview
@Composable
private fun CoinItemPreview() {
    CoinItemMainScreen(
        coinInfoGeneral = CoinInfoGeneral(
            id = 1803,
            name = "Ethereum",
            symbol = "BTC",
            price = 20000.0,
            todayDifference = 5.15,
            imageUrl = Uri.parse(
                "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png"
            ),
            priceInfo = emptyList()
        )
    ) { _, _ -> }
}