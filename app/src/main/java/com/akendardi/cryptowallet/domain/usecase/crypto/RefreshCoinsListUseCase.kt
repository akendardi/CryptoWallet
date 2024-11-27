package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoRepositoryGeneralInfo
import javax.inject.Inject

class RefreshCoinsListUseCase @Inject constructor(
    private val cryptoRepositoryGeneralInfo: CryptoRepositoryGeneralInfo
) {
    suspend operator fun invoke() =
        cryptoRepositoryGeneralInfo.refreshCoinsList()
}