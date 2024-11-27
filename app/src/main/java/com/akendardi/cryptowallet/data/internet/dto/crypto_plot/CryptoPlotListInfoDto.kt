package com.akendardi.cryptowallet.data.internet.dto.crypto_plot

import com.google.gson.annotations.SerializedName

data class CryptoPlotListInfoDto(
    @SerializedName("Data") val listPrices: List<CryptoPlotInfoDto>?
)
