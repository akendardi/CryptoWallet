package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.entity.CryptoCurrency

interface CryptoRepository {

    suspend fun loadAllCoinsList(page: Int = 0): List<CryptoCurrency>

    suspend fun buyCoin(
        transactionId: Int,
        currencyId: Int,
        count: Double
    ): Boolean

    suspend fun sellCoin(
        transactionId: Int,
        currencyId: Int,
        count: Double
    ): Boolean

}