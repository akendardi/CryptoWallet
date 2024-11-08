package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoRepository
import javax.inject.Inject

class RefreshCoinsListUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) {
    suspend operator fun invoke() =
        cryptoRepository.refreshCoinsList()
}