package com.akendardi.cryptowallet.domain.entity

import android.net.Uri

data class SearchCoinInfo(
    val name: String,
    val symbol: String,
    val imageUrl: Uri

)
