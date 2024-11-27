package com.akendardi.cryptowallet.domain.usecase.operations

import com.akendardi.cryptowallet.domain.repository.CoinOperationsRepository
import javax.inject.Inject

class LoadInfoForBuyingUseCase @Inject constructor(
    private val repository: CoinOperationsRepository
) {
    suspend operator fun invoke(symbol: String) = repository.getInfoForBuying(symbol)

    fun getInfoForBuying() = repository.coinOperationResult
}