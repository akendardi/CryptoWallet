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
import com.akendardi.cryptowallet.domain.entity.CoinInfo
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.CoinItemImageMainScreen
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.CoinDifferenceAndPriceMainScreen
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.CoinNameAndSymbolMainScreen
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.CoinPriceGraphMainScreen
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.getDifferenceColor

@Composable
fun CoinItemContentMainScreen(
    coinInfo: CoinInfo,
    onItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(80.dp)
            .clickable {
                onItemClicked(coinInfo.symbol)
            },
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

            CoinItemImageMainScreen(
                imageUrl = coinInfo.imageUrl
            )

            CoinNameAndSymbolMainScreen(
                name = coinInfo.name,
                symbol = coinInfo.symbol,
                modifier = Modifier.weight(0.8f)
            )

            CoinPriceGraphMainScreen(
                dataPoints = coinInfo.priceInfo,
                color = getDifferenceColor(coinInfo.todayDifference),
                modifier = Modifier.weight(0.8f)
            )

            CoinDifferenceAndPriceMainScreen(
                coinInfo = coinInfo
            )

        }
    }
}
