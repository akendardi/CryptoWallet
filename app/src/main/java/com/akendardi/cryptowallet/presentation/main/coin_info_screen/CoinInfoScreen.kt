package com.akendardi.cryptowallet.presentation.main.coin_info_screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun CoinInfoScreen(
    modifier: Modifier = Modifier,
    symbol: String
) {
    val viewModel = hiltViewModel<CoinInfoViewModel, CoinInfoViewModel.Factory>(
        creationCallback = {factory -> factory.create(symbol)}
    )
    val state by viewModel.state.collectAsState()

    CoinInfoScreenContent(
        state = state,
    )
}

@Composable
fun CoinInfoScreenContent(
    state: CoinInfoScreenState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                CoinImageCoinInfoScreen(
                    imageUri = state.coinInfoState.imageUri
                )
                CoinNameAndSymbolCoinInfoScreen(
                    modifier = Modifier.weight(1f),
                    name = state.coinInfoState.name,
                    symbol = state.coinInfoState.symbol
                )
                CoinPriceCoinInfoScreen(
                    modifier = Modifier.weight(1f),
                    price = state.coinInfoState.price
                )
            }
        }

    }
}

@Composable
fun CoinImageCoinInfoScreen(
    imageUri: Uri,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .clip(CircleShape)
            .size(72.dp),
        painter = rememberAsyncImagePainter(
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUri)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build()
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CoinNameAndSymbolCoinInfoScreen(
    name: String,
    symbol: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 24.sp
        )
        Text(
            text = symbol,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp
        )
    }
}

@Composable
fun CoinPriceCoinInfoScreen(
    price: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = price,
        textAlign = TextAlign.End,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        style = MaterialTheme.typography.titleMedium
    )
}


@Preview
@Composable
private fun CoinInfoPreview() {
    CoinInfoScreenContent(
        state = CoinInfoScreenState(
            coinInfoState = CoinInfoState(
                name = "Ethereum",
                symbol = "BTC",
                imageUri = Uri.parse("https://s2.coinmarketcap.com/static/img/coins/64x64/1.png"),
                price = "12312321"
            )
        )
    )
}