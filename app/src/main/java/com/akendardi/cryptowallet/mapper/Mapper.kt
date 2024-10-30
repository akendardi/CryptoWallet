package com.akendardi.cryptowallet.mapper

import android.net.Uri
import com.akendardi.cryptowallet.data.internet.dto.crypto.CurrencyDataItemDto
import com.akendardi.cryptowallet.data.internet.dto.crypto.plot.CryptoPlotListInfoDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_search.SearchedCoinInfoDto
import com.akendardi.cryptowallet.domain.entity.CoinInfo
import com.akendardi.cryptowallet.domain.entity.SearchCoinInfo

fun CurrencyDataItemDto.toEntity(plotInfoDto: CryptoPlotListInfoDto): CoinInfo {
    val prices = mutableListOf<Double>()
    plotInfoDto.listPrices.forEach{
        prices.add(it.closePrice)
    }
    return CoinInfo(
        id = coinInfo.id,
        name = coinInfo.name,
        symbol = coinInfo.symbol,
        price = priceInfoDto.usd.price,
        todayDifference = priceInfoDto.usd.dayDiffInPct,
        imageUrl = Uri.parse(coinInfo.imageUrl),
        priceInfo = prices
    )
}

fun SearchedCoinInfoDto.toEntity(): SearchCoinInfo {
    return SearchCoinInfo(
        name = name,
        symbol = symbol,
        imageUrl = Uri.parse(imageUrl)
    )
}