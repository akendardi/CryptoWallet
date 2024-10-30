package com.akendardi.cryptowallet.presentation.main.home_screen.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.domain.entity.SearchCoinInfo

@Composable
fun SearchCoinItem(
    modifier: Modifier = Modifier,
    searchCoinInfo: SearchCoinInfo
) {
    Card(
        modifier = modifier
            .height(80.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            CoinItemImage(imageUrl = searchCoinInfo.imageUrl)

            NameAndSymbol(
                name = searchCoinInfo.name,
                symbol = searchCoinInfo.symbol
            )
        }
    }
}

@Preview
@Composable
private fun SearchCoinInfoPreview() {
    SearchCoinItem(
        searchCoinInfo = SearchCoinInfo(
            name = "Ethereum",
            symbol = "BTC",
            imageUrl = Uri.parse("https://s2.coinmarketcap.com/static/img/coins/64x64/1.png")
        )
    )
}