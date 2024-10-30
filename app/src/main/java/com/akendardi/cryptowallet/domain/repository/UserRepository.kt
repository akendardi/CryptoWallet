package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.entity.CoinInfo
import kotlinx.coroutines.flow.Flow

interface UserRepository {


    suspend fun loadUsersCoinsListRemote(userId: Int): Flow<List<CoinInfo>>

    suspend fun loadUsersCoinsListLocal(): Flow<List<CoinInfo>>


    suspend fun addToBalance(
        userId: Int,
        amount: Double
    )

    suspend fun withdrawFromBalance(
        userId: Int,
        amount: Double
    )


}