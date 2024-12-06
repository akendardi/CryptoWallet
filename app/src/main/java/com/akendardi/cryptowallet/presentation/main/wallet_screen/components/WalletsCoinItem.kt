package com.akendardi.cryptowallet.presentation.main.wallet_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.main.home_screen.components.title.ImageWithShimmerEffect
import com.akendardi.cryptowallet.presentation.main.wallet_screen.PriceDifference
import com.akendardi.cryptowallet.presentation.main.wallet_screen.PurchasedCoinUI
import com.akendardi.cryptowallet.presentation.theme.NegativeDifferenceColor
import com.akendardi.cryptowallet.presentation.theme.PositiveDifferenceColor

@Composable
fun WalletsCoinItem(
    coin: PurchasedCoinUI,
    onCoinClick: (symbol: String, name: String) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                onCoinClick(coin.symbol, coin.name)
            },
        colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ImageWithShimmerEffect(
                modifier = Modifier.size(58.dp),
                uri = coin.imageUrl
            )
            CoinNameAndCount(
                modifier = Modifier.weight(2f),
                name = coin.name,
                count = coin.count
            )
            CoinPriceAndDifference(
                modifier = Modifier.weight(3f),
                price = coin.totalPrice,
                difference = coin.dollarsDifference,
                percentageDifference = coin.percentageDifference,
                differenceType = coin.differenceType
            )

        }
    }
}

@Composable
fun CoinNameAndCount(
    name: String,
    count: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.surfaceVariant,
            text = count,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CoinPriceAndDifference(
    price: String,
    difference: String,
    percentageDifference: String,
    differenceType: PriceDifference,
    modifier: Modifier = Modifier
) {

    val textColor = when(differenceType){
        PriceDifference.POSITIVE -> PositiveDifferenceColor
        PriceDifference.NEGATIVE -> NegativeDifferenceColor
        PriceDifference.NONE -> Color.Gray
    }

    val backgroundTextColor = textColor.copy(alpha = 0.15f)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            text = price,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Card(
                modifier = Modifier,
                colors = CardDefaults.cardColors().copy(containerColor = backgroundTextColor)
            ) {
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "$difference | $percentageDifference",
                    color = textColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall
                )


            }
        }


    }
}

@Preview
@Composable
private fun Preview() {
    WalletsCoinItem(
        coin =
        PurchasedCoinUI(
            name = "Bitcoinasdas",
            imageUrl = "",
            totalPrice = "$1 000 932.00",
            percentageDifference = "+100.00%",
            differenceType = PriceDifference.NONE,
            count = "1.51 BTC",
            dollarsDifference = "+$48 952.67"
        ),
        { _, _ -> },
    )
}