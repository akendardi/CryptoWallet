package com.akendardi.cryptowallet.data.repositories.coin_operations

import com.akendardi.cryptowallet.data.internet.api.AssetsCoinsApiService
import com.akendardi.cryptowallet.data.internet.dto.user.BalanceInfoDto
import com.akendardi.cryptowallet.domain.repository.CoinOperationsRepository
import com.akendardi.cryptowallet.domain.states.coin_operations.CoinOperationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CoinOperationsRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val apiService: AssetsCoinsApiService
) : CoinOperationsRepository {

    private val _coinOperationResult =
        MutableStateFlow<CoinOperationResult>(CoinOperationResult.Initial)
    override val coinOperationResult = _coinOperationResult.asStateFlow()


    override suspend fun getInfoForBuying(symbol: String) {
        _coinOperationResult.emit(CoinOperationResult.LoadingOperation)
        val userId = auth.currentUser?.uid ?: ""
        val snapshot = database.reference
            .child("users")
            .child(userId).child("balance")
            .get().await()

        val balanceDto = snapshot.getValue(BalanceInfoDto::class.java) ?: return

        val freeBalance = balanceDto.freeBalance ?: 0.0
        val coinDto = apiService.loadDetailCoinInfo(symbol).data.values.first()

        val price = coinDto.price
        val name = coinDto.name
        val coinImage = coinDto.logoUrl

        val result = CoinOperationResult.InfoLoaded(
            name = name,
            freeBalance = freeBalance,
            currentPrice = price,
            coinImage = coinImage
        )

        _coinOperationResult.emit(result)

    }

    override suspend fun buyCoin(symbol: String, count: Double) {
        TODO("Not yet implemented")
    }

    override suspend fun sellCoin(symbol: String, count: Double) {
        TODO("Not yet implemented")
    }
}