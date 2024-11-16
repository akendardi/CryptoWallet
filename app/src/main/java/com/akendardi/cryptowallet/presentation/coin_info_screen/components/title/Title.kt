package com.akendardi.cryptowallet.presentation.coin_info_screen.components.title

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.coin_info_screen.CoinInfoState

@Composable
fun TitleCoinInfo(
    coinInfoState: CoinInfoState,
    isNotificationsEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        TitleBackButton(
            onBackButtonClick = {}
        )

        CoinNameAndSymbolCoinInfoScreen(
            modifier = Modifier.weight(1f),
            name = coinInfoState.name,
            symbol = coinInfoState.symbol
        )

        TitleNotificationsButton(
            isNotificationsEnabled = isNotificationsEnabled,
            onNotificationsButtonClick = {}
        )


    }
}

@Composable
fun TitleBackButton(
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier.size(50.dp),
        onClick = onBackButtonClick
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null
        )
    }
}

@Composable
fun TitleNotificationsButton(
    isNotificationsEnabled: Boolean,
    onNotificationsButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier.size(50.dp),
        onClick = onNotificationsButtonClick
    ) {
        val imageVector = if (isNotificationsEnabled) {
            Icons.Default.NotificationsActive
        } else {
            Icons.Default.NotificationsNone
        }
        Icon(
            imageVector = imageVector,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null
        )
    }
}