package com.akendardi.cryptowallet.data.repositories.crypto

import com.akendardi.cryptowallet.data.internet.api.DataCoinsApiService
import com.akendardi.cryptowallet.domain.repository.CryptoDetailInfoRepository
import com.akendardi.cryptowallet.domain.states.crypto.CryptoInfoLoadingResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CryptoDetailInfoRepositoryImpl @Inject constructor(
    private val dataCoinsApiService: DataCoinsApiService
) : CryptoDetailInfoRepository {

    private val _coinInfo =
        MutableStateFlow<CryptoInfoLoadingResult>(CryptoInfoLoadingResult.Initial)
    override val coinInfo: StateFlow<CryptoInfoLoadingResult> = _coinInfo.asStateFlow()

    override suspend fun loadCoinInfo(symbol: String) {
        startLoading()
        try {
            val priceResponse = dataCoinsApiService.loadCurrentPrice(symbol)

        } catch (e: Exception) {
            emitError()
        }
    }

    private suspend fun startLoading() {
        _coinInfo.emit(CryptoInfoLoadingResult.Loading)
    }

    private suspend fun emitError() {
        _coinInfo.emit(CryptoInfoLoadingResult.Error)
    }
}