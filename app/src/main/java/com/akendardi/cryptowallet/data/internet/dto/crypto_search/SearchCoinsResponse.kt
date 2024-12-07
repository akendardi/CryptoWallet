package com.akendardi.cryptowallet.data.internet.dto.crypto_search

import com.google.gson.annotations.SerializedName

data class SearchCoinsResponse (
    @SerializedName("Data") val listSearchedCoinInfoDto: ListSearchedCoinInfoDto
)


