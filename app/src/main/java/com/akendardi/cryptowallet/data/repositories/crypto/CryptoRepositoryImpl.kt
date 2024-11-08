package com.akendardi.cryptowallet.data.repositories.crypto

import com.akendardi.cryptowallet.data.internet.api.CoinsApiService
import com.akendardi.cryptowallet.data.internet.api.SearchCoinsApiService
import com.akendardi.cryptowallet.domain.entity.CoinInfo
import com.akendardi.cryptowallet.domain.entity.SearchCoinInfo
import com.akendardi.cryptowallet.domain.repository.CryptoRepository
import com.akendardi.cryptowallet.mapper.toEntityMainScreen
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
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


    override suspend fun loadAllCoins(page: Int) {
        val newCoins = loadAllCoinsList(page)
        addToCoinsList(_topCoins.value, newCoins)
    }


    override suspend fun refreshCoinsList() {
        val newCoins = loadAllCoinsList(0)
        _topCoins.emit(newCoins)
    }

    private suspend fun loadAllCoinsList(page: Int): List<CoinInfo> = coroutineScope {
        val response = coinsApiService.loadAllCoins(limit = 15, page = page)

        response.data
            .filter { it.priceInfoDto != null }
            .map { coinData ->
                async {
                    val plotInformation =
                        coinsApiService.loadHistoricalInfo(fsym = coinData.coinInfo.symbol)

                    if (plotInformation.data.listPrices == null) {
                        null
                    } else {
                        coinData.toEntityMainScreen(plotInformation.data)
                    }
                }
            }
            .awaitAll()
            .filterNotNull()
    }

    override suspend fun searchCoins(query: String) {
        val response = searchCoinsApiService.searchCoinsWithString(query)
        val coins = response.listSearchedCoinInfoDto
            .listSearchedCoinInfoDto
            .filter { it.imageUrl != null }
            .map { it.toEntityMainScreen() }
        _searchCoins.emit(coins)
    }

    private suspend fun addToCoinsList(
        coins: List<CoinInfo>,
        newCoins: List<CoinInfo>
    ) {
        coins.plus(newCoins).let { _topCoins.emit(it) }
    }
}