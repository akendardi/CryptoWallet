package com.akendardi.cryptowallet.presentation.main.home_screen.components.balance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.presentation.main.wallet_screen.BalanceUI
import com.akendardi.cryptowallet.presentation.main.wallet_screen.PriceDifference
import com.akendardi.cryptowallet.presentation.theme.NegativeDifferenceColor
import com.akendardi.cryptowallet.presentation.theme.PositiveDifferenceColor


@Composable
fun UserBalanceInfo(
    balanceUI: BalanceUI
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            BalanceValue(
                value = balanceUI.totalBalance
            )

            BalanceDifference(
                difference = balanceUI.dollarsDifference,
                percentageDifference = balanceUI.percentageDifference,
                differenceType = balanceUI.priceDifference
            )


        }


    }
}

@Composable
fun BalanceDifference(
    difference: String,
    percentageDifference: String,
    differenceType: PriceDifference,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
) {

    val textColor = when (differenceType) {
        PriceDifference.POSITIVE -> PositiveDifferenceColor
        PriceDifference.NEGATIVE -> NegativeDifferenceColor
        PriceDifference.NONE -> Color.Gray
    }

    val backgroundTextColor = textColor.copy(alpha = 0.15f)

    Box(
        modifier = modifier
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
                style = textStyle
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    UserBalanceInfo(
        balanceUI =

        BalanceUI(
            totalBalance = "$1 000 932.00",
            freeBalance = "$1 000 932.00",
            percentageDifference = "+100.00%",
            priceDifference = PriceDifference.POSITIVE,
            dollarsDifference = "+$48 952.67"
        )
    )
}

@Composable
fun BalanceValue(
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = value,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        style = MaterialTheme.typography.titleLarge
    )
}
