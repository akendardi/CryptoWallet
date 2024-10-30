package com.akendardi.cryptowallet.data.repositories.crypto

import android.net.Uri
import android.util.Log
import com.akendardi.cryptowallet.data.internet.api.CoinsApiService
import com.akendardi.cryptowallet.data.internet.api.SearchCoinsApiService
import com.akendardi.cryptowallet.domain.entity.CoinInfo
import com.akendardi.cryptowallet.domain.entity.SearchCoinInfo
import com.akendardi.cryptowallet.domain.repository.CryptoRepository
import com.akendardi.cryptowallet.mapper.toEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val coinsApiService: CoinsApiService,
    private val searchCoinsApiService: SearchCoinsApiService
) : CryptoRepository {

    private val _topCoins = MutableStateFlow<List<CoinInfo>>(listOf())
    override val topCoins = _topCoins.asStateFlow()

    private val _searchCoins = MutableStateFlow<List<SearchCoinInfo>>(listOf())
    override val searchedCoins = _searchCoins.asStateFlow()

    override suspend fun loadAllCoinsList(page: Int) {
        val coins = _topCoins.value
        val response = coinsApiService.loadAllCoins(limit = 15, page = page)
        Log.d("HUIHUI", "loadAllCoinsList: $page")
        val newCoins = response.data
            .filter {
                it?.priceInfoDto != null
            }
            .map {
                val plotInformation = coinsApiService.loadHistoricalInfo(fsym = it.coinInfo.symbol)
                try {
                    it.toEntity(plotInformation.data)
                } catch (e: Exception) {
                    Log.d("HUIHUI", "loadAllCoinsList: ${e.message},      $it")
                    CoinInfo(0, "", "", 0.0, 0.0, Uri.parse(""), emptyList())
                }

            }

        coins.plus(newCoins).let { _topCoins.emit(it) }
    }

    override suspend fun searchCoins(query: String) {
        val response = searchCoinsApiService.searchCoinsWithString(query)
        val coins = response.listSearchedCoinInfoDto
            .listSearchedCoinInfoDto
            .filter { it.imageUrl != null }
            .map { it.toEntity() }
        _searchCoins.emit(coins)
    }
}