package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.BoxLoading
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy.components.BalanceCardBuying
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy.components.BuyingButton
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.components.CoinCard
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.components.CoinsOperationsTextField
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.components.OperationResults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyCoinScreenContent(
    state: BuyCoinScreenState,
    onValueChanged: (String) -> Unit,
    onBackClick: () -> Unit,
    onBuyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.buying),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
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
        if (state.isFirstLoading) {
            BoxLoading()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(
                        top = 20.dp,
                        start = 20.dp,
                        end = 20.dp
                    )
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(rememberScrollState())
                    .imePadding(),
                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                BalanceCardBuying(state.currentFreeBalance)

                CoinCard(
                    name = state.name,
                    currentPrice = state.currentPrice,
                    imageUrl = state.imageUrl
                )
                CoinsOperationsTextField(
                    isEnabled = state.isCanBuy,
                    amount = state.count,
                    onValueChanged = onValueChanged,
                    error = state.error
                )

                OperationResults(totalCount = state.totalCount)


                BuyingButton(
                    isEnabled = state.error == "" && state.count.isNotEmpty() && state.isCanBuy,
                    onClick = onBuyClick
                )

                if (!state.isCanBuy) {
                    Text(
                        text = stringResource(R.string.buy_screen_account_not_verificated),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }

            }
        }
    }
}