package com.akendardi.cryptowallet.data.internet.dto.crypto.plot

import com.google.gson.annotations.SerializedName

data class CryptoPlotInfoResponseDto(
    @SerializedName("Data") val data: CryptoPlotListInfoDto
)
