package com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.domain.entity.coin_info_general.CoinInfoGeneral
import com.akendardi.cryptowallet.presentation.theme.NegativeDifferenceColor
import com.akendardi.cryptowallet.presentation.theme.PositiveDifferenceColor

@Composable
fun CoinDifferenceAndPriceMainScreen(
    coinInfoGeneral: CoinInfoGeneral,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = coinInfoGeneral.getFormattedDifference(),
            color = getDifferenceColor(coinInfoGeneral.todayDifference),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Text(
            text = coinInfoGeneral.getFormattedPrice(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

fun getDifferenceColor(todayDifference: Double): Color {
    return if (todayDifference > 0) {
        PositiveDifferenceColor
    } else {
        NegativeDifferenceColor
    }
}
