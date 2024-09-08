package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.entity.CryptoCurrency
import kotlinx.coroutines.flow.Flow

interface UserRepository {


    suspend fun loadUsersCoinsListRemote(userId: Int): Flow<List<CryptoCurrency>>

    suspend fun loadUsersCoinsListLocal(): Flow<List<CryptoCurrency>>

    suspend fun createAccount(
        name: String,
        password: String,
        email: String
    )

    suspend fun logInAccount(
        email: String,
        password: String
    )

    suspend fun logOutFromAccount()

    suspend fun addToBalance(
        userId: Int,
        amount: Double
    )

    suspend fun withdrawFromBalance(
        userId: Int,
        amount: Double
    )


}