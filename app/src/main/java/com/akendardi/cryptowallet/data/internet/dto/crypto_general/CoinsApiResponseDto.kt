package com.akendardi.cryptowallet.data.internet.dto.crypto_general

import com.google.gson.annotations.SerializedName

data class CoinsApiResponseDto(
    @SerializedName("Data") val data: List<CurrencyDataItemDto>
)