package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoRepository
import javax.inject.Inject

class GetAllCoinsListUseCase @Inject constructor(
    private val repository: CryptoRepository
) {
    operator fun invoke() = repository.topCoins
}