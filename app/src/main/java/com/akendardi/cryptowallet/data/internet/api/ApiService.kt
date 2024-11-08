package com.akendardi.cryptowallet.data.internet.api

import com.akendardi.cryptowallet.data.internet.dto.crypto.CoinsApiResponseDto
import com.akendardi.cryptowallet.data.internet.dto.crypto.plot.CryptoPlotInfoResponseDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_search.SearchCoinsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinsApiService {

    @GET("data/top/totalvolfull?tsym=USD")
    suspend fun loadAllCoins(
        @Query("limit") limit: Int = 50,
        @Query("page") page: Int = 0
    ): CoinsApiResponseDto

    @GET("data/v2/histohour")
    suspend fun loadHistoricalInfo(
        @Query("fsym") fsym: String,
        @Query("tsym") tsym: String = "USD",
        @Query("limit") limit: Int = 23,
    ): CryptoPlotInfoResponseDto

}

interface SearchCoinsApiService {

    @GET("asset/v1/search")
    suspend fun searchCoinsWithString(
        @Query("search_string") searchString: String
    ): SearchCoinsResponse
}