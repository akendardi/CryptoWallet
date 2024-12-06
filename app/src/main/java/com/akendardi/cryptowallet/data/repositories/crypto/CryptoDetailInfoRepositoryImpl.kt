package com.akendardi.cryptowallet.data.repositories.crypto

import android.util.Log
import com.akendardi.cryptowallet.data.internet.api.AssetsCoinsApiService
import com.akendardi.cryptowallet.data.internet.api.DataCoinsApiService
import com.akendardi.cryptowallet.domain.repository.CryptoDetailInfoRepository
import com.akendardi.cryptowallet.domain.states.crypto.CryptoInfoLoadingResult
import com.akendardi.cryptowallet.mapper.responsesToCoinInfoDetail
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CryptoDetailInfoRepositoryImpl @Inject constructor(
    private val dataCoinsApiService: DataCoinsApiService,
    private val assetsCoinsApiService: AssetsCoinsApiService
) : CryptoDetailInfoRepository {


    private val _coinInfo =
        MutableStateFlow<CryptoInfoLoadingResult>(CryptoInfoLoadingResult.Initial)
    override val coinInfo: StateFlow<CryptoInfoLoadingResult> = _coinInfo.asStateFlow()




    override suspend fun loadCoinInfo(symbol: String) {
        startLoading()
        try {
            loadData(symbol)
        } catch (e: Exception) {
            emitError(e)
        }
    }

    private suspend fun loadData(symbol: String) {
        coroutineScope {
            val infoDeferred = async { assetsCoinsApiService.loadDetailCoinInfo(symbol) }
            val hourDeferred = async { dataCoinsApiService.loadHourHistoricalInfo(symbol) }
            val dayDeferred = async { dataCoinsApiService.loadDayHistoricalInfo(symbol) }

            val infoResponse = infoDeferred.await()
            val hourResponse = hourDeferred.await()
            val dayResponse = dayDeferred.await()



            val detailCoinInfo = responsesToCoinInfoDetail(
                infoResponse,
                hourResponse,
                dayResponse
            )

            _coinInfo.emit(CryptoInfoLoadingResult.Success(detailCoinInfo))
        }
    }

    private suspend fun startLoading() {
        Log.d("TEST_FLOW", "startLoading repository")
        _coinInfo.emit(CryptoInfoLoadingResult.Loading)
    }

    private suspend fun emitError(e: Exception) {
        _coinInfo.emit(CryptoInfoLoadingResult.Error(e))
    }
}