package com.akendardi.cryptowallet.data.repositories.crypto

import com.akendardi.cryptowallet.domain.entity.CryptoCoin
import com.akendardi.cryptowallet.domain.entity.Transaction
import com.akendardi.cryptowallet.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor() : CryptoRepository {
    override suspend fun loadAllCoinsList(page: Int): Flow<List<CryptoCoin>> {
        TODO("Not yet implemented")
    }

    override suspend fun buyCoin(userId: Int, currencyId: Int, count: Double): Transaction {
        TODO("Not yet implemented")
    }

    override suspend fun sellCoin(userId: Int, currencyId: Int, count: Double): Transaction {
        TODO("Not yet implemented")
    }
}