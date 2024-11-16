package com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.domain.entity.coin_info_search.CoinInfoSearch
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.CoinItemImageMainScreen
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.CoinNameAndSymbolMainScreen

@Composable
fun SearchCoinItemContent(
    coinInfoSearch: CoinInfoSearch,
    onItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(80.dp)
            .padding(4.dp)
            .clickable { onItemClicked(coinInfoSearch.symbol) },
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

            CoinItemImageMainScreen(imageUrl = coinInfoSearch.imageUrl)

            CoinNameAndSymbolMainScreen(
                name = coinInfoSearch.name,
                symbol = coinInfoSearch.symbol
            )
        }
    }
}