package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoRepository
import javax.inject.Inject

class SellCoinUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {
    suspend operator fun invoke(
        userId: Int,
        currencyId: Int,
        amount: Double
    ) =
        cryptoRepository.sellCoin(
            userId,
            currencyId,
            amount
        )
}