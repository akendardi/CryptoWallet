package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy

import BuyingTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy.components.BalanceCard
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy.components.BuyingButton
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy.components.BuyingResult
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy.components.CoinCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyCoinScreenContent(
    state: BuyCoinScreenState,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(
                    start = 20.dp,
                    end = 20.dp
                )
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.SpaceAround

        ) {
            Text(
                text = "Покупка",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )

            BalanceCard(state.currentFreeBalance)

            CoinCard(
                name = state.name,
                currentPrice = state.currentPrice,
                imageUrl = state.imageUrl
            )
            BuyingTextField(
                amount = state.amount,
                onValueChanged = onValueChanged,
                error = state.error
            )




            if (state.totalCount != "") {
                BuyingResult(totalCount = state.totalCount)
            }

            BuyingButton(totalCount = state.totalCount, onClick = {

            })

        }

    }

}

@Preview
@Composable
private fun AlertDialogBuyCoinPreview() {
    BuyCoinScreenContent(
        state = BuyCoinScreenState(
            isLoading = false,
            name = "Bitcoin",
            currentPrice = "$1000",
            currentFreeBalance = "$124.34",
            amount = "412.34",
            totalCount = "0.00043 BTC",
            error = "a"
        ),
        onValueChanged = {}
    )
}