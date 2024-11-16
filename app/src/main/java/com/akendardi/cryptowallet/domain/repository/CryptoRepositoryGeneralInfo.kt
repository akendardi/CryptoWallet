package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.entity.coin_info_general.CoinInfoGeneral
import com.akendardi.cryptowallet.domain.entity.coin_info_search.CoinInfoSearch
import kotlinx.coroutines.flow.StateFlow

interface CryptoRepositoryGeneralInfo {

    val topCoins: StateFlow<List<CoinInfoGeneral>>
    val searchedCoins: StateFlow<List<CoinInfoSearch>>

    suspend fun loadAllCoins(page: Int = 0)

    suspend fun refreshCoinsList()


    suspend fun searchCoins(query: String)

}