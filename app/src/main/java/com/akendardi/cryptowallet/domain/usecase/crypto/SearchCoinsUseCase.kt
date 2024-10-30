package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoRepository
import javax.inject.Inject

class SearchCoinsUseCase @Inject constructor(
    private val repository: CryptoRepository
) {

    suspend operator fun invoke(query: String) = repository.searchCoins(query)
}