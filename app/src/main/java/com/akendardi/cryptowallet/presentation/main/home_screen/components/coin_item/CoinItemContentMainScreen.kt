package com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.domain.entity.coin_info_general.CoinInfoGeneral
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.CoinItemImage
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.CoinDifferenceAndPriceMainScreen
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.CoinNameAndSymbol
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.CoinPriceGraphMainScreen
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components.getDifferenceColor

@Composable
fun CoinItemContentMainScreen(
    coinInfoGeneral: CoinInfoGeneral,
    onItemClicked: (symbol: String, name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(80.dp)
            .clickable {
                onItemClicked(
                    coinInfoGeneral.symbol,
                    coinInfoGeneral.name
                )
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

            CoinItemImage(
                modifier = Modifier.size(58.dp),
                imageUrl = coinInfoGeneral.imageUrl
            )

            CoinNameAndSymbol(
                name = coinInfoGeneral.name,
                symbol = coinInfoGeneral.symbol,
                modifier = Modifier.weight(0.8f)
            )

            CoinPriceGraphMainScreen(
                dataPoints = coinInfoGeneral.priceInfo,
                color = getDifferenceColor(coinInfoGeneral.todayDifference),
                modifier = Modifier.weight(0.8f)
            )

            CoinDifferenceAndPriceMainScreen(
                coinInfoGeneral = coinInfoGeneral
            )

        }
    }
}
