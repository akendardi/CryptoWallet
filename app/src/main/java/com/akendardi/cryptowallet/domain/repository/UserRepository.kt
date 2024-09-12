package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.entity.CryptoCoin
import kotlinx.coroutines.flow.Flow

interface UserRepository {


    suspend fun loadUsersCoinsListRemote(userId: Int): Flow<List<CryptoCoin>>

    suspend fun loadUsersCoinsListLocal(): Flow<List<CryptoCoin>>


    suspend fun addToBalance(
        userId: Int,
        amount: Double
    )

    suspend fun withdrawFromBalance(
        userId: Int,
        amount: Double
    )


}