package com.akendardi.cryptowallet.presentation.main.home_screen.components.title

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.akendardi.cryptowallet.presentation.main.home_screen.components.shimmer_effects.ShimmerAnimationProfileImage


@Composable
fun ProfileImage(
    uri: String?,
    modifier: Modifier = Modifier,
) {

    SubcomposeAsyncImage(
        model = uri,
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape),

        contentScale = ContentScale.Crop
    ) {
        when (this.painter.state) {
            is AsyncImagePainter.State.Loading -> {
                ShimmerAnimationProfileImage()
            }

            else -> {
                SubcomposeAsyncImageContent()
            }
        }
    }
}