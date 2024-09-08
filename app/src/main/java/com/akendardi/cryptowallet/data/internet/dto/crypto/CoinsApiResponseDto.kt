package com.akendardi.cryptowallet.data.internet.dto.crypto

import com.google.gson.annotations.SerializedName

data class CoinsApiResponseDto(
    @SerializedName("Data") val data: List<CurrencyDataItemDto>
)