package com.akendardi.cryptowallet.domain.states.coin_operations

sealed class CoinOperationResult {

    data object Initial : CoinOperationResult()

    data object LoadingInfo : CoinOperationResult()

    data class InfoLoaded(
        val name: String,
        val freeBalance: Double,
        val lockedBalanceForCurrentCoin: Double,
        val currentCoinsCount: Double,
        val currentPrice: Double,
        val coinImage: String,
        val isAccountVerificated: Boolean
    ) : CoinOperationResult()

    data object LoadingOperation : CoinOperationResult()

    data object Error : CoinOperationResult()

    data class Success(val transactionId: Int) : CoinOperationResult()


}