package com.akendardi.cryptowallet.data.internet.dto.crypto_search

import com.google.gson.annotations.SerializedName

data class ListSearchedCoinInfoDto(
    @SerializedName("LIST") val listSearchedCoinInfoDto: List<SearchedCoinInfoDto>
)