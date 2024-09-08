package com.akendardi.cryptowallet.domain.entity

import java.util.Date

data class CryptoDayHistory(
    val symb: String,
    val closePrice: Double,
    val date: Date
)