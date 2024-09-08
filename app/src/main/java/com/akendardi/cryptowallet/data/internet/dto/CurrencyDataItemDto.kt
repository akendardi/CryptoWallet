package com.akendardi.cryptowallet.data.internet.dto

import com.google.gson.annotations.SerializedName

data class CurrencyDataItemDto(
    @SerializedName("CoinInfo") val coinInfo: CoinInfoDto,
    @SerializedName("RAW") val priceInfoDto: PriceInfoDto
)