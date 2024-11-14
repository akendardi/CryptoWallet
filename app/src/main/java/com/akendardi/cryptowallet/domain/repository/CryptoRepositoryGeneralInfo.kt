package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.entity.CoinInfoGeneral
import com.akendardi.cryptowallet.domain.entity.CoinInfoSearch
import kotlinx.coroutines.flow.StateFlow

interface CryptoRepositoryGeneralInfo {

    val topCoins: StateFlow<List<CoinInfoGeneral>>
    val searchedCoins: StateFlow<List<CoinInfoSearch>>

    suspend fun loadAllCoins(page: Int = 0)

    suspend fun refreshCoinsList()


    suspend fun searchCoins(query: String)

}