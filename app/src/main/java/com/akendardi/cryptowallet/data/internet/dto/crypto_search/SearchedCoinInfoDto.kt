package com.akendardi.cryptowallet.data.internet.dto.crypto_search

import com.google.gson.annotations.SerializedName

data class SearchedCoinInfoDto(
    @SerializedName("SYMBOL") val symbol: String,
    @SerializedName("NAME") val name: String,
    @SerializedName("LOGO_URL") val imageUrl: String?
)