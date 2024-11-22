package com.akendardi.cryptowallet.presentation.coin_info_screen.information.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.CoinInfoDetailState

@Composable
fun CoinInfoInformationTitle(
    coinInfoDetailState: CoinInfoDetailState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = coinInfoDetailState.price,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = stringResource(R.string.last_time_update, coinInfoDetailState.lastDateUpdate),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CoinInfoInformationTitle(
        coinInfoDetailState = CoinInfoDetailState(
            name = "Ethereum",
            symbol = "BTC",
            price = "20000.0",
            lastDateUpdate = "19.00"
        )
    )
}