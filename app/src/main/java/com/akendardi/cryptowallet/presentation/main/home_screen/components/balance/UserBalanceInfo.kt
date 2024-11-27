package com.akendardi.cryptowallet.presentation.main.home_screen.components.balance

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.presentation.theme.NegativeColor
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
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Log.d("TAG_TAG", "ProfileImage: compose")
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(profileGradientColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = "Текущий баланс",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 14.sp
                )


                BalanceValue(
                    value = totalBalance
                )

                DifferenceValue(
                    value = differencePercent,
                    isPositiveDifference = true
                )
                DifferenceValue(
                    value = differencePercent,
                    isPositiveDifference = false
                )


            }

            Column {
                BalanceButton(
                    text = "Пополнить",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
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
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun DifferenceValue(
    modifier: Modifier = Modifier,
    value: String,
    isPositiveDifference: Boolean
) {
    val color = if (isPositiveDifference) {
        PositiveDifferenceColor
    } else {
        NegativeColor
    }
    Text(
        modifier = modifier,
        text = value,
        color = color,
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
fun BalanceButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(15),
        onClick = {}
    ) {
        Text(text = text, color = MaterialTheme.colorScheme.onBackground)
    }
}