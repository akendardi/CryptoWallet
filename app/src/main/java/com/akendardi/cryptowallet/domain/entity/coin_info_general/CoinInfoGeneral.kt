package com.akendardi.cryptowallet.domain.entity.coin_info_general

import android.net.Uri
import java.util.Locale

data class CoinInfoGeneral(
    val id: Int,
    val name: String,
    val symbol: String,
    val price: Double,
    val todayDifference: Double,
    val imageUrl: Uri,
    val priceInfo: List<Double>
) {
    fun getFormattedDifference(): String {
        val str = if (todayDifference > 0) "+" else ""
        return str + String.format(Locale.getDefault(), "%.2f", todayDifference).replace(".", ",") + "%"
    }

    fun getFormattedPrice(): String {
        return String.format(Locale.getDefault(), "%.2f", price).replace(".", ",") + "$"
    }
}
