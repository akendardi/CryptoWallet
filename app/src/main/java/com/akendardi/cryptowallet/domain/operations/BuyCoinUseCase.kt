package com.akendardi.cryptowallet.domain.operations

import com.akendardi.cryptowallet.domain.repository.CoinOperationsRepository
import javax.inject.Inject

class BuyCoinUseCase @Inject constructor(
    private val repository: CoinOperationsRepository
) {
    suspend operator fun invoke(symbol: String, amount: Double) = repository.buyCoin(symbol, amount)
}