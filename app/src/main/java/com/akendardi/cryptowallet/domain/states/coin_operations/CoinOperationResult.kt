package com.akendardi.cryptowallet.domain.states.coin_operations

sealed class CoinOperationResult {

    data object Initial : CoinOperationResult()

    data object LoadingInfo : CoinOperationResult()

    data class InfoLoaded(
        val name: String,
        val freeBalance: Double,
        val currentPrice: Double,
        val coinImage: String
    ) : CoinOperationResult()

    data object LoadingOperation : CoinOperationResult()

    data object Error : CoinOperationResult()

    data class Success(val transactionId: Int) : CoinOperationResult()


}