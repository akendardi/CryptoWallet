package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoRepository
import javax.inject.Inject

class BuyCoinUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {
    suspend operator fun invoke(
        userId: Int,
        currencyId: Int,
        amount: Double
    ) =
        cryptoRepository.buyCoin(
            userId,
            currencyId,
            amount
        )
}