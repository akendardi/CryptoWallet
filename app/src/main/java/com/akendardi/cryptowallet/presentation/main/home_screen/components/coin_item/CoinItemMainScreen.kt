package com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.akendardi.cryptowallet.domain.entity.CoinInfo


@Composable
fun CoinItemMainScreen(
    coinInfo: CoinInfo,
    onItemClicked: (String) -> Unit
) {
    CoinItemContentMainScreen(
        coinInfo = coinInfo,
        onItemClicked = onItemClicked
    )
}

@Preview
@Composable
private fun CoinItemPreview() {
    CoinItemMainScreen(
        coinInfo = CoinInfo(
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
    ){}
}