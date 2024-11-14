package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoRepositoryGeneralInfo
import javax.inject.Inject

class LoadAllCoinsListUseCase @Inject constructor(
    private val cryptoRepositoryGeneralInfo: CryptoRepositoryGeneralInfo
) {
    suspend operator fun invoke(page: Int) =
        cryptoRepositoryGeneralInfo.loadAllCoins(page)
}