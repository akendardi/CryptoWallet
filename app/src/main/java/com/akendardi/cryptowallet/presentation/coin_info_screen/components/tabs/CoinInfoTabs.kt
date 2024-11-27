package com.akendardi.cryptowallet.presentation.coin_info_screen.components.tabs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.coin_info_screen.CurrentCoinInfoScreen

@Composable
fun CoinInfoTabs(
    selectedTab: CurrentCoinInfoScreen,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val tabs = listOf(
        "Информация",
        "Транзакции"
    )

    TabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = selectedTab.ordinal,
        indicator = { tabPositions ->
            SecondaryIndicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab.ordinal])
                    .height(2.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab.ordinal == index,
                onClick = { onTabSelected(index) },
                text = { Text(text = title) },
                selectedContentColor = MaterialTheme.colorScheme.onBackground,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)

            )
        }
    }

}