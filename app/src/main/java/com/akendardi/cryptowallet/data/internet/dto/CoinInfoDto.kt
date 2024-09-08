package com.akendardi.cryptowallet.data.internet.dto

import com.google.gson.annotations.SerializedName

data class CoinInfoDto(
    @SerializedName("Id") val id: String,
    @SerializedName("Name") val shortName: String,
    @SerializedName("FullName") val name: String,
    @SerializedName("ImageUrl") private val shortImageUrl: String
){
    companion object{
        private const val BASE_IMAGE_URL = "https://www.cryptocompare.com"
    }
    val imageUrl: String
        get() = "$BASE_IMAGE_URL$shortImageUrl"
}
