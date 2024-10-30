package com.akendardi.cryptowallet.data.internet.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object CoinsApiFactory {

    private const val BASE_URL = "https://min-api.cryptocompare.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val coinsApiService: CoinsApiService = retrofit.create()
}

object SearchCoinsApiFactory {

    private const val BASE_URL = "https://data-api.cryptocompare.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchCoinsApiService: SearchCoinsApiService = retrofit.create()
}