package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.entity.CoinInfo
import com.akendardi.cryptowallet.domain.entity.SearchCoinInfo
import kotlinx.coroutines.flow.StateFlow

interface CryptoRepository {

    val topCoins: StateFlow<List<CoinInfo>>
    val searchedCoins: StateFlow<List<SearchCoinInfo>>

    suspend fun loadAllCoins(page: Int = 0)

    suspend fun refreshCoinsList()


    suspend fun searchCoins(query: String)

}