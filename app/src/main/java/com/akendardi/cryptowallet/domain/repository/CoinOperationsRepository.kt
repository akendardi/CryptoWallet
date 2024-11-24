package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.states.coin_operations.CoinOperationResult
import kotlinx.coroutines.flow.StateFlow

interface CoinOperationsRepository {

    val coinOperationResult: StateFlow<CoinOperationResult>

    suspend fun getInfoForBuying(symbol: String)

    suspend fun buyCoin(symbol: String, count: Double)

    suspend fun sellCoin(symbol: String, count: Double)
}