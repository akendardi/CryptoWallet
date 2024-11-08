package com.akendardi.cryptowallet.mapper

import android.net.Uri
import android.util.Log
import com.akendardi.cryptowallet.data.internet.dto.crypto.CurrencyDataItemDto
import com.akendardi.cryptowallet.data.internet.dto.crypto.plot.CryptoPlotListInfoDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_search.SearchedCoinInfoDto
import com.akendardi.cryptowallet.domain.entity.CoinInfo
import com.akendardi.cryptowallet.domain.entity.SearchCoinInfo

fun CurrencyDataItemDto.toEntityMainScreen(plotInfoDto: CryptoPlotListInfoDto): CoinInfo {
    val prices = mutableListOf<Double>()
    Log.d("TVARINA", "toEntityMainScreen: $coinInfo")
    Log.d("TVARINA", "toEntityMainScreen: $plotInfoDto")
    plotInfoDto.listPrices?.forEach {
        prices.add(it.closePrice)
    }
    return CoinInfo(
        id = coinInfo.id,
        name = coinInfo.name,
        symbol = coinInfo.symbol,
        price = priceInfoDto?.usd?.price ?: throw Exception("Price is null"),
        todayDifference = priceInfoDto.usd.dayDiffInPct,
        imageUrl = Uri.parse(coinInfo.imageUrl),
        priceInfo = prices
    )
}

fun SearchedCoinInfoDto.toEntityMainScreen(): SearchCoinInfo {
    return SearchCoinInfo(
        name = name,
        symbol = symbol,
        imageUrl = Uri.parse(imageUrl)
    )
}