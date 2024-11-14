package com.akendardi.cryptowallet.data.internet.dto.crypto_price

import com.google.gson.annotations.SerializedName

data class CoinPriceResponseDto(
    @SerializedName("USD") val price: Double
)
