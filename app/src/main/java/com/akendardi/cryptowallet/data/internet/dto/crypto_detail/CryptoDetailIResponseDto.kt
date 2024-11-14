package com.akendardi.cryptowallet.data.internet.dto.crypto_detail

import com.google.gson.annotations.SerializedName

data class DetailCoinInfoResponseDto(
    @SerializedName("Data") val data: Map<String, DetailCoinInfoDto>
)

data class DetailCoinInfoDto(
    @SerializedName("NAME") val name: String,
    @SerializedName("SYMBOL") val symbol: String,
    @SerializedName("LOGO_URL") val logoUrl: String
)

