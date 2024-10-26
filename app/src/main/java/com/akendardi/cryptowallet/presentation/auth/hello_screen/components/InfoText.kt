package com.akendardi.cryptowallet.presentation.auth.hello_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.R


@Composable
fun InfoText(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.hello_info_text),
        textAlign = TextAlign.Start,
        fontSize = 15.sp,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .padding(
                PaddingValues(
                    start = 20.dp
                )
            ),
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun HeadText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            start = 20.dp,
            bottom = 10.dp
        )
    ) {
        Text(
            text = stringResource(R.string.welcome),
            textAlign = TextAlign.Start,
            fontSize = 30.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "в Crypto Wallet",
            textAlign = TextAlign.Start,
            fontSize = 40.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

}