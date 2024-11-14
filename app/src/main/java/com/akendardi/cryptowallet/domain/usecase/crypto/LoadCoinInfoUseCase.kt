package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoDetailInfoRepository
import javax.inject.Inject

class LoadCoinInfoUseCase @Inject constructor(
    private val repository: CryptoDetailInfoRepository
) {
    suspend operator fun invoke(fsym: String) = repository.loadCoinInfo(fsym)

    fun subscribeCoinInfo() = repository.coinInfo
}