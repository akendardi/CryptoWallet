package com.akendardi.cryptowallet.data.internet.dto

import com.google.gson.annotations.SerializedName

data class UsdDto(
    @SerializedName("PRICE") val PRICE: Double,//цена
    @SerializedName("LASTUPDATE") val LASTUPDATE: Long,//дата обновления
    @SerializedName("OPENHOUR") val OPENHOUR: Double,//Цена открытия ластового часа
    @SerializedName("HIGHHOUR") val HIGHHOUR: Double,//макс за час
    @SerializedName("LOWHOUR") val LOWHOUR: Double,//мин за час
    @SerializedName("VOLUMEDAY") val VOLUMEDAY: Double, //объемы за день
    @SerializedName("VOLUMEDAYTO") val VOLUMEDAYTO: Double, //объемы в долларах
    @SerializedName("OPENDAY") val OPENDAY: Double, //открытие дня
    @SerializedName("HIGHDAY") val HIGHDAY: Double, //макс за день
    @SerializedName("LOWDAY") val LOWDAY: Double, //мин за день
    @SerializedName("CHANGEDAY") val CHANGEDAY: Double, //изменение за день
    @SerializedName("CHANGEPCTDAY") val CHANGEPCTDAY: Double, //изменение в процентах
    @SerializedName("SUPPLY") val SUPPLY: Double, //всего монет
    @SerializedName("MKTCAP") val MKTCAP: Double, //капитализация
    @SerializedName("TOTALVOLUME24H") val TOTALVOLUME24H: Double, //общий объем торговли за 24 часа
    @SerializedName("TOTALVOLUME24HTO") val TOTALVOLUME24HTO: Double, //общий объем торговли за 24 часа в долларах
)
