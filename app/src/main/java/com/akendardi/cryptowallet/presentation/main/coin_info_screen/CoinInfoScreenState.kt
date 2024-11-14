package com.akendardi.cryptowallet.presentation.main.coin_info_screen

import android.net.Uri

data class CoinInfoScreenState(
    val coinInfoState: CoinInfoState = CoinInfoState(),
)

data class CoinInfoState(
    val symbol: String = "",
    val name: String = "",
    val imageUri: Uri = Uri.parse(""),
    val price: String = "",
)