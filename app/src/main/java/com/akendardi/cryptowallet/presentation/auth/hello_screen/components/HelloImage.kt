package com.akendardi.cryptowallet.presentation.auth.hello_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import com.akendardi.cryptowallet.R


@Composable
fun HelloImage() {
    Image(
        bitmap = ImageBitmap.imageResource(R.drawable.hello_screen),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
    )

}