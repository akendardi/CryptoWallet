package com.akendardi.cryptowallet.domain.entity

import android.net.Uri

data class CoinInfoSearch(
    val name: String,
    val symbol: String,
    val imageUrl: Uri

)
