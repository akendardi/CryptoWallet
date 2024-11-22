package com.akendardi.cryptowallet.presentation.coin_info_screen.information.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.GraphicType

@Composable
fun GraphicTypeSwitcher(
    onGraphTypeChange: () -> Unit,
    graphicType: GraphicType,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        SwitchTypeGraphic(
            onGraphTypeChange = onGraphTypeChange,
            type = graphicType
        )
    }
}

@Composable
fun SwitchTypeGraphic(
    type: GraphicType,
    onGraphTypeChange: () -> Unit,
    modifier: Modifier = Modifier
) {

    val isDailyGraph = type == GraphicType.DAY

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
            .clickable(onClick = onGraphTypeChange),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.hour_symbol),
            modifier = Modifier
                .weight(1f),
            style = MaterialTheme.typography.titleSmall.copy(
                color = if (isDailyGraph) Color.Gray else MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.day_symbol),
            modifier = Modifier
                .weight(1f),
            style = MaterialTheme.typography.titleSmall.copy(
                color = if (isDailyGraph) MaterialTheme.colorScheme.primary else Color.Gray
            ),
            textAlign = TextAlign.Center
        )
    }

}