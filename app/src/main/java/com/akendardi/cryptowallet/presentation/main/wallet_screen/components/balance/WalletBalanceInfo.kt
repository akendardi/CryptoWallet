package com.akendardi.cryptowallet.presentation.main.wallet_screen.components.balance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.presentation.main.home_screen.components.balance.BalanceDifference
import com.akendardi.cryptowallet.presentation.main.wallet_screen.BalanceUI
import com.akendardi.cryptowallet.presentation.main.wallet_screen.PriceDifference

@Composable
fun WalletBalanceInfo(
    balanceUI: BalanceUI,
    onAddToBalanceClick: () -> Unit,
    onRemoveFromBalanceClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = balanceUI.totalBalance,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )


            BalanceDifference(
                difference = balanceUI.dollarsDifference,
                percentageDifference = balanceUI.percentageDifference,
                differenceType = balanceUI.priceDifference,
                textStyle = MaterialTheme.typography.titleMedium
            )

            WalletButtons(
                onAddToBalanceClick = onAddToBalanceClick,
                onRemoveFromBalanceClick = onRemoveFromBalanceClick
            )


        }
    }
}


@Preview
@Composable
private fun WalletPreview() {
    WalletBalanceInfo(
        balanceUI = BalanceUI(
            totalBalance = "$1 000 932.00",
            freeBalance = "$1 000 932.00",
            percentageDifference = "100.00%",
            priceDifference = PriceDifference.POSITIVE,
            dollarsDifference = "$48 952.67"
        ), { /*TODO*/ }, { /*TODO*/ }
    )
}