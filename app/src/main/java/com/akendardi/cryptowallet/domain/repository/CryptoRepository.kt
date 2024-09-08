package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.entity.CryptoCurrency

interface CryptoRepository {

    suspend fun loadAllCoinsList(page: Int = 0): List<CryptoCurrency>

    suspend fun loadUsersCoinsList(): List<CryptoCurrency>

    suspend fun buyCoin(
        currencyId: Int,
        count: Double
    ): Boolean

    suspend fun sellCoin(
        currencyId: Int,
        count: Double
    ): Boolean


}