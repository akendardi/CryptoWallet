package com.akendardi.cryptowallet.data.internet.dto.crypto_general

import com.google.gson.annotations.SerializedName

data class CurrencyDataItemDto(
    @SerializedName("CoinInfo") val coinInfo: CoinInfoDto,
    @SerializedName("RAW") val detailPriceInfoDto: DetailPriceInfoDto?
)