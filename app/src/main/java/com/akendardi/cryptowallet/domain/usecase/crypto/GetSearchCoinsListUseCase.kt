package com.akendardi.cryptowallet.domain.usecase.crypto

import com.akendardi.cryptowallet.domain.repository.CryptoRepository
import javax.inject.Inject

class GetSearchCoinsListUseCase @Inject constructor(
    private val repository: CryptoRepository
) {

    operator fun invoke() = repository.searchedCoins
}