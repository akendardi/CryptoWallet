package com.akendardi.cryptowallet.data.internet.dto.crypto.plot

import com.google.gson.annotations.SerializedName

data class CryptoPlotInfoDto(
    @SerializedName("time") val time: Long,
    @SerializedName("high") val maxPrice: Double,
    @SerializedName("low") val minPrice: Double,
    @SerializedName("open") val openPrice: Double,
    @SerializedName("close") val closePrice: Double
)
