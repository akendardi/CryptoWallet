package com.akendardi.cryptowallet.domain.usecase.operations

import com.akendardi.cryptowallet.domain.repository.OperationsCoinRepository
import javax.inject.Inject

class OperationsUseCase @Inject constructor(
    private val repository: OperationsCoinRepository
) {
    fun getInfo() = repository.operationResult

    suspend fun startLoadingInfo(symbol: String) = repository.getInfo(symbol)

    suspend fun buyCoin(symbol: String, amount: Double) = repository.buyCoin(symbol, amount)

    suspend fun sellCoin(symbol: String, amount: Double) = repository.sellCoin(symbol, amount)
}