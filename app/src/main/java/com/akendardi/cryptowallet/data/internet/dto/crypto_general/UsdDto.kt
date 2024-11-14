package com.akendardi.cryptowallet.data.internet.dto.crypto_general

import com.google.gson.annotations.SerializedName

data class UsdDto(
    @SerializedName("PRICE") val price: Double,//цена
    @SerializedName("LASTUPDATE") val lastUpdateDate: Long,//дата обновления
    @SerializedName("OPENHOUR") val lastHourOpenPrice: Double,//Цена открытия ластового часа
    @SerializedName("HIGHHOUR") val lastHourMaxPrice: Double,//макс за час
    @SerializedName("LOWHOUR") val lastHourMinPrice: Double,//мин за час
    @SerializedName("VOLUMEDAY") val volumeDay: Double, //объемы за день
    @SerializedName("VOLUMEDAYTO") val volumeDayInUsd: Double, //объемы в долларах
    @SerializedName("OPENDAY") val dayOpenPrice: Double, //открытие дня
    @SerializedName("HIGHDAY") val dayMaxPrice: Double, //макс за день
    @SerializedName("LOWDAY") val dayLowPrice: Double, //мин за день
    @SerializedName("CHANGEDAY") val dayDiff: Double, //изменение за день
    @SerializedName("CHANGEPCTDAY") val dayDiffInPct: Double, //изменение в процентах
    @SerializedName("SUPPLY") val countCoins: Double, //всего монет
    @SerializedName("MKTCAP") val fullPriceAllCoinsInUsd: Double, //капитализация
    @SerializedName("TOTALVOLUME24H") val allDayVolume: Double, //общий объем торговли за 24 часа
    @SerializedName("TOTALVOLUME24HTO") val allDayVolumeInUsd: Double, //общий объем торговли за 24 часа в долларах
)
