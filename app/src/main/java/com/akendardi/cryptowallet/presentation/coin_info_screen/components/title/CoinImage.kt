package com.akendardi.cryptowallet.presentation.coin_info_screen.components.title

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun CoinImageCoinInfoScreen(
    imageUri: String,
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