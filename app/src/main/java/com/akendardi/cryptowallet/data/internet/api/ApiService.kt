package com.akendardi.cryptowallet.data.internet.api

import com.akendardi.cryptowallet.data.internet.dto.CoinsApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/top/totalvolfull?tsym=USD")
    suspend fun loadlCurrencies(
        @Query("limit") limit: Int = 50,
        @Query("page") page: Int = 0
    ): CoinsApiResponseDto
}