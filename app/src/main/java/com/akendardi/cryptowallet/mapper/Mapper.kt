package com.akendardi.cryptowallet.mapper

import android.net.Uri
import com.akendardi.cryptowallet.data.internet.dto.crypto_detail.DetailCoinInfoResponseDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_general.CurrencyDataItemDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_plot.CryptoPlotInfoResponseDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_plot.CryptoPlotListInfoDto
import com.akendardi.cryptowallet.data.internet.dto.crypto_search.SearchedCoinInfoDto
import com.akendardi.cryptowallet.data.internet.dto.user.BalanceInfoDto
import com.akendardi.cryptowallet.data.internet.dto.user.PurchasedCoinDto
import com.akendardi.cryptowallet.data.internet.dto.user.TransactionDto
import com.akendardi.cryptowallet.domain.entity.coin_info_detail.CoinInfoDetail
import com.akendardi.cryptowallet.domain.entity.coin_info_detail.PricePoint
import com.akendardi.cryptowallet.domain.entity.coin_info_general.CoinInfoGeneral
import com.akendardi.cryptowallet.domain.entity.coin_info_search.CoinInfoSearch
import com.akendardi.cryptowallet.domain.entity.user_info.balance.PurchasedCoin
import com.akendardi.cryptowallet.domain.entity.user_info.balance.UsersBalance
import com.akendardi.cryptowallet.domain.entity.user_info.transactions.Transaction
import com.akendardi.cryptowallet.domain.entity.user_info.transactions.TransactionType
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.PriceConverter
import com.akendardi.cryptowallet.presentation.main.transactions_screen.components.TransactionUi
import com.akendardi.cryptowallet.presentation.main.wallet_screen.BalanceUI
import com.akendardi.cryptowallet.presentation.main.wallet_screen.PriceDifference
import com.akendardi.cryptowallet.presentation.main.wallet_screen.PurchasedCoinUI
import kotlin.math.absoluteValue

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
        name = name, symbol = symbol, imageUrl = Uri.parse(imageUrl)
    )
}

fun transactionDtoToEntity(transactionDto: TransactionDto): Transaction {
    return Transaction(
        transactionId = transactionDto.transactionId,
        userId = transactionDto.userId,
        type = if (transactionDto.type == TransactionDto.TYPE_BUY) TransactionType.Buy else TransactionType.Sell,
        symbol = transactionDto.symbol,
        count = transactionDto.count,
        price = transactionDto.price,
        amount = transactionDto.amount,
        time = transactionDto.time,
        coinImage = transactionDto.imageUrl
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
            time = it.time, price = it.closePrice
        )
    } ?: emptyList()

    val listDayPoints = dayPlotInfo.data.listPrices?.map {
        PricePoint(
            time = it.time, price = it.closePrice
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

fun transactionToUi(transaction: Transaction): TransactionUi {
    val amount =
        if (transaction.type == TransactionType.Sell) "+${PriceConverter.formatPrice(transaction.amount)}" else "-${
            PriceConverter.formatPrice(transaction.amount)
        }"
    return TransactionUi(
        transactionId = transaction.transactionId.toString(),
        type = transaction.type,
        coinImage = transaction.coinImage,
        amount = amount,
        count = transaction.count.toString()
    )
}

fun purchasedCoinDtoToEntity(
    purchasedCoinDto: PurchasedCoinDto, currentPrice: Double
): PurchasedCoin {
    return PurchasedCoin(
        symbol = purchasedCoinDto.symbol,
        name = purchasedCoinDto.name,
        count = purchasedCoinDto.count,
        buyPrice = purchasedCoinDto.buyPrice,
        currentPrice = currentPrice,
        imageUrl = purchasedCoinDto.imageUrl
    )
}

fun balanceDtoToEntity(
    balanceInfoDto: BalanceInfoDto, newPurchasedCoins: List<PurchasedCoin>
): UsersBalance {
    return UsersBalance(
        freeBalance = balanceInfoDto.freeBalance,
        purchasedCoins = newPurchasedCoins
    )
}

fun balanceToUi(balance: UsersBalance): BalanceUI {
    val totalBalance =
        balance.freeBalance + balance.purchasedCoins.sumOf { it.currentPrice * it.count }
    val difference =
        balance.purchasedCoins.sumOf { it.count * (it.currentPrice - it.buyPrice) }

    val percentDifference =
        if (totalBalance != 0.0) (difference / (balance.purchasedCoins.sumOf { it.count * it.buyPrice } + balance.freeBalance)) * 100 else 0.0
    val percentToCompare = (percentDifference * 100).toInt() / 100.0
    val differenceType = if (percentToCompare > 0) {
        PriceDifference.POSITIVE
    } else if (percentToCompare == 0.0) {
        PriceDifference.NONE
    } else {
        PriceDifference.NEGATIVE
    }

    val formattedDifference = PriceConverter.formatPrice(difference.absoluteValue)

    val differenceSign = when (differenceType) {
        PriceDifference.POSITIVE -> "+"
        PriceDifference.NEGATIVE -> "-"
        PriceDifference.NONE -> "~"
    }

    return BalanceUI(
        totalBalance = PriceConverter.formatPrice(totalBalance),
        freeBalance = PriceConverter.formatPrice(balance.freeBalance),
        percentageDifference = "%.2f".format(percentDifference.absoluteValue) + "%",
        priceDifference = differenceType,
        dollarsDifference = "$differenceSign$formattedDifference",
    )

}

fun purchasedCoinToUI(purchasedCoin: PurchasedCoin): PurchasedCoinUI {
    val difference = purchasedCoin.currentPrice - purchasedCoin.buyPrice

    val count = purchasedCoin.count
    val percentDifference = (difference / purchasedCoin.buyPrice) * 100

    val dollarsDifference = ((difference.absoluteValue * count) * 100).toInt() / 100.0
    val differenceType = if (dollarsDifference > 0) {
        PriceDifference.POSITIVE
    } else if (dollarsDifference == 0.0) {
        PriceDifference.NONE
    } else {
        PriceDifference.NEGATIVE
    }

    val dollarsDifferenceFormatted = PriceConverter.formatPrice(dollarsDifference)

    return PurchasedCoinUI(
        symbol = purchasedCoin.symbol,
        name = purchasedCoin.name,
        count = "$count ${purchasedCoin.symbol}",
        totalPrice = PriceConverter.formatPrice(purchasedCoin.currentPrice * count),
        percentageDifference = "%.2f".format(percentDifference.absoluteValue) + "%",
        differenceType = differenceType,
        dollarsDifference = when (differenceType) {
            PriceDifference.POSITIVE -> "+$dollarsDifferenceFormatted"
            PriceDifference.NEGATIVE -> "-$dollarsDifferenceFormatted"
            PriceDifference.NONE -> "~$dollarsDifferenceFormatted"
        },
        imageUrl = purchasedCoin.imageUrl
    )
}

