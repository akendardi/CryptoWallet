package com.akendardi.cryptowallet.presentation.main.home_screen.components.balance

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.presentation.theme.PositiveDifferenceColor
import com.akendardi.cryptowallet.presentation.theme.profileGradientColor


@Composable
fun UserBalanceInfo(
    totalBalance: String,
    differencePercent: String,
    onDepositButtonClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(

        )
    ) {
        Log.d("TAG_TAG", "ProfileImage: compose")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(profileGradientColor)
                .padding(12.dp)
        ) {
            Text(
                text = "Текущий баланс",
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 14.sp
            )


            BalanceValue(
                value = totalBalance
            )

            DifferenceValue(
                value = differencePercent
            )

        }

    }
}

@Composable
fun BalanceValue(
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = value,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun DifferenceValue(
    modifier: Modifier = Modifier,
    value: String
) {
    Text(
        modifier = modifier,
        text = value,
        color = PositiveDifferenceColor,
        style = MaterialTheme.typography.titleMedium,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
private fun UserBalanceInfoPreview() {
    UserBalanceInfo(
        totalBalance = "83,745.12$",
        differencePercent = "+10.2% (+327$)",
        onDepositButtonClick = {}
    )
}

@Composable
fun RowScope.BalanceButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(10.dp),
        shape = RoundedCornerShape(15),
        onClick = {}
    ) {
        Text(text = text, color = MaterialTheme.colorScheme.onBackground)
    }
}