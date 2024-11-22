package com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils

import java.text.NumberFormat
import java.util.Locale

class PriceConverter {

    companion object {


        fun selectSixPrices(data: List<Double>): List<String> {
            if (data.size <= 6) return formatPricesForScreen(data)
            val step = (data.max() - data.min()) / 5
            val list = List(6) { i -> (data.min() + step * i) }

            return formatPricesForScreen(list.reversed())
        }

        private fun formatPricesForScreen(prices: List<Double>): List<String> {
            val maxValue = prices.maxOrNull() ?: 0.0
            return if (maxValue in 0.0..1.0) {
                prices.map { String.format(locale = Locale.getDefault(), format = "%.5f", it) }
            } else if (maxValue <= 1000) {
                prices.map { String.format(locale = Locale.getDefault(), format = "%.2f", it) }
            } else {
                prices.map {
                    String.format(
                        locale = Locale.getDefault(),
                        format = "%.2f",
                        it / 1000
                    ) + "k"
                }
            }
        }

        fun formatPrice(price: Double): String {
            val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
                minimumFractionDigits = 2
                maximumFractionDigits = 2
            }
            return "$" + formatter.format(price)
        }
    }
}