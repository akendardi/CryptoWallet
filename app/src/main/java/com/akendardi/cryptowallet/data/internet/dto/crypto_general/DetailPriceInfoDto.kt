package com.akendardi.cryptowallet.data.internet.dto.crypto_general

import com.google.gson.annotations.SerializedName

data class DetailPriceInfoDto(
    @SerializedName("USD") val usd: UsdDto
)