package com.akendardi.cryptowallet.data.internet.dto.user

data class BalanceInfoDto(
    val freeBalance: Double = 0.0,
    val purchasedCoins: List<PurchasedCoinDto> = listOf(),
    val transactions: List<TransactionDto> = listOf()
)

data class PurchasedCoinDto(
    val symbol: String = "",
    val name: String = "",
    val buyPrice: Double = 0.0,
    val count: Double = 0.0,
    val imageUrl: String = ""
)

data class TransactionDto(
    val transactionId: Int = 0,
    val type: String = "",
    val userId: String = "",
    val symbol: String = "",
    val imageUrl: String = "",
    val price: Double = 0.0,
    val count: Double = 0.0,
    val amount: Double = 0.0,
    val time: Long = 0L
){
    companion object{
        const val TYPE_BUY = "buy"
        const val TYPE_SELL = "sell"
    }
}
