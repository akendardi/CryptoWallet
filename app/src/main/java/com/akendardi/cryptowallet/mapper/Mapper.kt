package com.akendardi.cryptowallet.mapper

import android.net.Uri
import com.akendardi.cryptowallet.data.internet.dto.crypto_detail.DetailCoinInfoResponseDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_general.CurrencyDataItemDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_plot.CryptoPlotInfoResponseDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_plot.CryptoPlotListInfoDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_search.SearchedCoinInfoDto
import com.akendardi.cryptowallet.domain.entity.coin_info_detail.CoinInfoDetail
import com.akendardi.cryptowallet.domain.entity.coin_info_detail.PricePoint
import com.akendardi.cryptowallet.domain.entity.coin_info_general.CoinInfoGeneral
import com.akendardi.cryptowallet.domain.entity.coin_info_search.CoinInfoSearch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun CurrencyDataItemDto.toEntityMainScreen(plotInfoDto: CryptoPlotListInfoDto): CoinInfoGeneral {
    val prices = mutableListOf<Double>()
    plotInfoDto.listPrices?.forEach {
        prices.add(it.closePrice)
    }
    return CoinInfoGeneral(
        id = coinInfo.id,
        name = coinInfo.name,
        symbol = coinInfo.symbol,
        price = detailPriceInfoDto?.usd?.price ?: throw Exception("Price is null"),
        todayDifference = detailPriceInfoDto.usd.dayDiffInPct,
        imageUrl = Uri.parse(coinInfo.imageUrl),
        priceInfo = prices
    )
}

fun SearchedCoinInfoDto.toEntityMainScreen(): CoinInfoSearch {
    return CoinInfoSearch(
        name = name,
        symbol = symbol,
        imageUrl = Uri.parse(imageUrl)
    )
}

fun responsesToCoinInfoDetail(
    infoResponseDto: DetailCoinInfoResponseDto,
    hourPlotInfo: CryptoPlotInfoResponseDto,
    dayPlotInfo: CryptoPlotInfoResponseDto,
): CoinInfoDetail {
    val name = infoResponseDto.data.values.first().name
    val symbol = infoResponseDto.data.values.first().symbol
    val imageUrl = infoResponseDto.data.values.first().logoUrl
    val price = infoResponseDto.data.values.first().price
    val lastUpdate = infoResponseDto.data.values.first().lastUpdate

    val listHourPoints = hourPlotInfo.data.listPrices?.map {
        PricePoint(
            time = it.time,
            price = it.closePrice
        )
    } ?: emptyList()

    val listDayPoints = dayPlotInfo.data.listPrices?.map {
        PricePoint(
            time = it.time,
            price = it.closePrice
        )
    } ?: emptyList()

    return CoinInfoDetail(
        name = name,
        symbol = symbol,
        imageUrl = imageUrl,
        currentPrice = price,
        lastUpdate = lastUpdate,
        pricesHour = listHourPoints,
        pricesDay = listDayPoints
    )

}

fun Double.toFormattedPrice(): String {
    return "$" + String.format(Locale.getDefault(), "%.2f", this)
}

fun formatUnixTimestampsForDays(timestamps: List<Long>): List<List<String>> {
    val formatter = DateTimeFormatter.ofPattern("dd MMM E")
    val zoneId = ZoneId.systemDefault()

    return timestamps.map { timestamp ->
        val dateTime = Instant.ofEpochSecond(timestamp).atZone(zoneId)
        val formattedDate = dateTime.format(formatter)
        formattedDate.split(" ").map {
            it.replaceFirstChar { char ->
                if (char.isLowerCase()) char.titlecase(
                    Locale.ROOT
                ) else char.toString()
            }
        }
    }
}

fun formatUnixTimestampsForHours(timestamps: List<Long>): List<List<String>> {
    val formatter = DateTimeFormatter.ofPattern("HH dd MMM") // Формат: "часы день месяц"
    val zoneId = ZoneId.systemDefault()

    return timestamps.map { timestamp ->
        val dateTime = Instant.ofEpochSecond(timestamp).atZone(zoneId)
        val formattedDateTime = dateTime.format(formatter)

        // Разбиваем на список [часы, число, месяц]
        formattedDateTime.split(" ").map {
            it.replaceFirstChar { char ->
                if (char.isLowerCase()) char.titlecase(Locale.ROOT) else char.toString()
            }
        }
    }
}