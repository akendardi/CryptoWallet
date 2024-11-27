package com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun CoinItemImage(
    imageUrl: Uri,
    modifier: Modifier = Modifier
) {

    Image(
        modifier = modifier
            .clip(CircleShape),
        painter = rememberAsyncImagePainter(
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build()
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}