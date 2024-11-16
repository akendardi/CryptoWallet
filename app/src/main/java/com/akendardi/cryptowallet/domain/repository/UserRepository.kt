package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.entity.coin_info_general.CoinInfoGeneral
import kotlinx.coroutines.flow.Flow

interface UserRepository {


    suspend fun loadUsersCoinsListRemote(userId: Int): Flow<List<CoinInfoGeneral>>

    suspend fun loadUsersCoinsListLocal(): Flow<List<CoinInfoGeneral>>


    suspend fun addToBalance(
        userId: Int,
        amount: Double
    )

    suspend fun withdrawFromBalance(
        userId: Int,
        amount: Double
    )


}