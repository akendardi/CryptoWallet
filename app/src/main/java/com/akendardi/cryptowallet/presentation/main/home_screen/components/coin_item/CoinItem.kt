package com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item

import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.akendardi.cryptowallet.domain.entity.CoinInfo


@Composable
fun CoinItem(
    coinInfo: CoinInfo,
    onItemClicked: (String) -> Unit
) {
    CoinItemContent(
        coinInfo = coinInfo,
        onItemClicked = onItemClicked
    )
}

@Preview
@Composable
private fun CoinItemPreview() {
    CoinItem(
        coinInfo = CoinInfo(
            id = 1803,
            name = "Ethereum",
            symbol = "BTC",
            price = 20000.0,
            todayDifference = 5.15,
            imageUrl = Uri.parse(
                "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png"
            ),
            priceInfo = emptyList()
        )
    ){}
}