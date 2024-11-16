package com.akendardi.cryptowallet.data.internet.api

import com.akendardi.cryptowallet.data.internet.dto.crypto_detail.DetailCoinInfoResponseDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_general.CoinsApiResponseDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_plot.CryptoPlotInfoResponseDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_search.SearchCoinsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DataCoinsApiService {

    @GET("data/top/totalvolfull?tsym=USD")
    suspend fun loadAllCoins(
        @Query("limit") limit: Int = 50,
        @Query("page") page: Int = 0
    ): CoinsApiResponseDto

    @GET("data/v2/histohour")
    suspend fun loadHourHistoricalInfo(
        @Query("fsym") fsym: String,
        @Query("tsym") tsym: String = "USD",
        @Query("limit") limit: Int = 23,
    ): CryptoPlotInfoResponseDto

    @GET("data/v2/histoday")
    suspend fun loadDayHistoricalInfo(
        @Query("fsym") fsym: String,
        @Query("tsym") tsym: String = "USD",
        @Query("limit") limit: Int = 30,
    ): CryptoPlotInfoResponseDto

}

interface AssetsCoinsApiService {

    @GET("asset/v1/search")
    suspend fun searchCoinsWithString(
        @Query("search_string") searchString: String
    ): SearchCoinsResponse

    @GET("asset/v2/metadata")
    suspend fun loadDetailCoinInfo(
        @Query("assets") fsym: String
    ): DetailCoinInfoResponseDto
}