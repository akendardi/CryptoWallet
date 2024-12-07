package com.akendardi.cryptowallet.data.internet.dto.crypto_general

import com.google.gson.annotations.SerializedName

data class UsdDto(
    @SerializedName("PRICE") val price: Double,
    @SerializedName("CHANGEPCTDAY") val dayDiffInPct: Double,

)
