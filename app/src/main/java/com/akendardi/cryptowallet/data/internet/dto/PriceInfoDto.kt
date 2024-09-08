package com.akendardi.cryptowallet.data.internet.dto

import com.google.gson.annotations.SerializedName

data class PriceInfoDto(
    @SerializedName("USD") val usd: UsdDto
)
