package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoRepositoryGeneralInfo
import javax.inject.Inject

class GetAllCoinsListUseCase @Inject constructor(
    private val repository: CryptoRepositoryGeneralInfo
) {
    operator fun invoke() = repository.topCoins
}