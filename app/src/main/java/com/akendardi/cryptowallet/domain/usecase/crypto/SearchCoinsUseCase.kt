package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoRepositoryGeneralInfo
import javax.inject.Inject

class SearchCoinsUseCase @Inject constructor(
    private val repository: CryptoRepositoryGeneralInfo
) {

    suspend operator fun invoke(query: String) = repository.searchCoins(query)
}