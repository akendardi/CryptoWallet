package com.akendardi.cryptowallet.data.internet.api

import com.akendardi.cryptowallet.data.internet.dto.crypto.CoinsApiResponseDto
import com.akendardi.cryptowallet.data.internet.dto.crypto.plot.CryptoPlotInfoResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/top/totalvolfull?tsym=USD")
    suspend fun loadAllCurrencies(
        @Query("limit") limit: Int = 50,
        @Query("page") page: Int = 0
    ): CoinsApiResponseDto

    @GET("data/v2/histoday")
    suspend fun loadHistoricalInfo(
        @Query("fsym") fsym: String,
        @Query("tsym") tsym: String = "USD",
        @Query("limit") limit: Int = 30,
    ): CryptoPlotInfoResponseDto
}