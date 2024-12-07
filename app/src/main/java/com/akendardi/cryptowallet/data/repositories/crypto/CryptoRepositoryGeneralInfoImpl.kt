package com.akendardi.cryptowallet.data.repositories.crypto

import com.akendardi.cryptowallet.data.internet.api.AssetsCoinsApiService
import com.akendardi.cryptowallet.data.internet.api.DataCoinsApiService
import com.akendardi.cryptowallet.domain.entity.coin_info_general.CoinInfoGeneral
import com.akendardi.cryptowallet.domain.entity.coin_info_search.CoinInfoSearch
import com.akendardi.cryptowallet.domain.repository.CryptoRepositoryGeneralInfo
import com.akendardi.cryptowallet.mapper.toEntityMainScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CryptoRepositoryGeneralInfoImpl @Inject constructor(
    private val dataCoinsApiService: DataCoinsApiService,
    private val assetsCoinsApiService: AssetsCoinsApiService
) : CryptoRepositoryGeneralInfo {

    private val _topCoins = MutableStateFlow<List<CoinInfoGeneral>>(listOf())
    override val topCoins = _topCoins.asStateFlow()

    private val _searchCoins = MutableStateFlow<List<CoinInfoSearch>>(listOf())
    override val searchedCoins = _searchCoins.asStateFlow()


    override suspend fun loadAllCoins(page: Int) {
        val newCoins = loadAllCoinsList(page)
        addToCoinsList(_topCoins.value, newCoins)
    }


    override suspend fun refreshCoinsList() {
        val newCoins = loadAllCoinsList(0)
        _topCoins.emit(newCoins)
    }

    private suspend fun loadAllCoinsList(page: Int): List<CoinInfoGeneral> = coroutineScope {
        val response = dataCoinsApiService.loadAllCoins(limit = 8, page = page)

        response.data
            .filter { it.detailPriceInfoDto != null }
            .map { coinData ->
                async(Dispatchers.IO) {
                    val plotInformation =
                        dataCoinsApiService.loadHourHistoricalInfo(fsym = coinData.coinInfo.symbol)


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
        val response = assetsCoinsApiService.searchCoinsWithString(query)
        val coins = response.listSearchedCoinInfoDto
            .listSearchedCoinInfoDto
            .filter { it.imageUrl != null }
            .map { it.toEntityMainScreen() }
        _searchCoins.emit(coins)
    }

    private suspend fun addToCoinsList(
        coins: List<CoinInfoGeneral>,
        newCoins: List<CoinInfoGeneral>
    ) {
        coins.plus(newCoins).let { _topCoins.emit(it) }
    }
}