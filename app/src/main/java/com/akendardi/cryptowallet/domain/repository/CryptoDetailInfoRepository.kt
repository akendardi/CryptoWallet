package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.states.crypto.CryptoInfoLoadingResult
import kotlinx.coroutines.flow.StateFlow

interface CryptoDetailInfoRepository {

    val coinInfo: StateFlow<CryptoInfoLoadingResult>

    suspend fun loadCoinInfo(symbol: String)
}