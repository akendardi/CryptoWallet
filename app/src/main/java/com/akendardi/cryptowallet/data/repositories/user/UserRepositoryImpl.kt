package com.akendardi.cryptowallet.data.repositories.user

import com.akendardi.cryptowallet.domain.entity.coin_info_general.CoinInfoGeneral
import com.akendardi.cryptowallet.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
) : UserRepository {
    override suspend fun loadUsersCoinsListRemote(userId: Int): Flow<List<CoinInfoGeneral>> {
        TODO("Not yet implemented")
    }

    override suspend fun loadUsersCoinsListLocal(): Flow<List<CoinInfoGeneral>> {
        TODO("Not yet implemented")
    }

    override suspend fun addToBalance(userId: Int, amount: Double) {
        TODO("Not yet implemented")
    }

    override suspend fun withdrawFromBalance(userId: Int, amount: Double) {
        TODO("Not yet implemented")
    }
}