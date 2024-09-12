package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.entity.CryptoCoin
import com.akendardi.cryptowallet.domain.entity.Transaction
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    suspend fun loadAllCoinsList(page: Int = 0): Flow<List<CryptoCoin>>

    suspend fun buyCoin(
        userId: Int,
        currencyId: Int,
        count: Double
    ): Transaction

    suspend fun sellCoin(
        userId: Int,
        currencyId: Int,
        count: Double
    ): Transaction

}