package com.akendardi.cryptowallet.domain.states.crypto

import com.akendardi.cryptowallet.domain.entity.CoinInfoDetail

sealed class CryptoInfoLoadingResult {

    data object Initial : CryptoInfoLoadingResult()

    data object Loading : CryptoInfoLoadingResult()

    data object Error : CryptoInfoLoadingResult()

    data class Success(val coinInfo: CoinInfoDetail) : CryptoInfoLoadingResult()
}