package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoRepositoryGeneralInfo
import javax.inject.Inject

class GetSearchCoinsListUseCase @Inject constructor(
    private val repository: CryptoRepositoryGeneralInfo
) {

    operator fun invoke() = repository.searchedCoins
}