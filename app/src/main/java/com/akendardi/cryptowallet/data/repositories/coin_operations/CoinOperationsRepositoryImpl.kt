package com.akendardi.cryptowallet.data.repositories.coin_operations

import android.util.Log
import com.akendardi.cryptowallet.data.internet.api.AssetsCoinsApiService
import com.akendardi.cryptowallet.data.internet.dto.user.BalanceInfoDto
import com.akendardi.cryptowallet.data.internet.dto.user.PurchasedCoinDto
import com.akendardi.cryptowallet.data.internet.dto.user.TransactionDto
import com.akendardi.cryptowallet.domain.entity.user_info.transactions.TransactionType
import com.akendardi.cryptowallet.domain.repository.CoinOperationsRepository
import com.akendardi.cryptowallet.domain.states.coin_operations.CoinOperationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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

    init {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TEST_RESULT", "repository $coinOperationResult ")
            coinOperationResult.collect{
                Log.d("FLOW_BUY", "$it")
            }
        }


    }


    override suspend fun getInfoForBuying(symbol: String) {
        _coinOperationResult.emit(CoinOperationResult.LoadingInfo)
        val userId = auth.currentUser?.uid ?: ""
        val snapshot = database.reference
            .child("users")
            .child(userId)
            .child("balance")


        val newSnap = snapshot.get().await()

        val balanceDto = newSnap.getValue(BalanceInfoDto::class.java) ?: return

        val freeBalance = balanceDto.freeBalance ?: 0.0
        val coinDto = apiService.loadDetailCoinInfo(symbol).data.values.first()

        val price = coinDto.price
        val name = coinDto.name
        val coinImage = coinDto.logoUrl
        val isAccountVerificated = auth.currentUser?.isEmailVerified ?: false

        val result = CoinOperationResult.InfoLoaded(
            name = name,
            freeBalance = freeBalance,
            currentPrice = price,
            coinImage = coinImage,
            isAccountVerificated = isAccountVerificated
        )

        _coinOperationResult.emit(result)

    }

    override suspend fun buyCoin(symbol: String, amount: Double) {
        _coinOperationResult.emit(CoinOperationResult.LoadingOperation)
        val coinDto = apiService.loadDetailCoinInfo(symbol).data.values.first()
        val price = coinDto.price

        val count = amount / price
        val userId = auth.currentUser?.uid ?: ""
        val snapshot = database.reference
            .child("users")
            .child(userId)
            .child("balance")
            .get()
            .await()

        val balanceDto = snapshot.getValue(BalanceInfoDto::class.java) ?: return

        val oldListCoins = balanceDto.purchasedCoins ?: listOf()

        val currentPurchasedCoin = oldListCoins
            .find { it.symbol == symbol } ?: PurchasedCoinDto()

        val sumPrice = (currentPurchasedCoin.buyPrice ?: 0.0) * (currentPurchasedCoin.count
            ?: 0.0) + price * count
        val sumCount = (currentPurchasedCoin.count ?: 0.0) + count
        val newPrice = sumPrice / sumCount

        val newPurchasedCoin = PurchasedCoinDto(
            count = sumCount,
            buyPrice = newPrice,
            symbol = symbol
        )

        val newList = oldListCoins.toMutableList().apply {
            remove(currentPurchasedCoin)
            add(newPurchasedCoin)
        }

        val transaction = getTransaction(
            symbol = symbol,
            count = count,
            price = price,
            amount = amount,
            date = System.currentTimeMillis()
        )

        val oldTransactions = database.reference
            .child("users")
            .child(userId)
            .child("balance")
            .child("transactions")
            .get()
            .await()
            .getValue<List<TransactionDto>>() ?: listOf()

        val newTransactions = oldTransactions.toMutableList().apply {
            add(transaction)
        }
        val newBalance = balanceDto.copy(
            lockedBalance = (balanceDto.lockedBalance ?: 0.0) + amount,
            freeBalance = (balanceDto.freeBalance ?: 0.0) - amount,
            totalBalance = (balanceDto.totalBalance ?: 0.0),
            purchasedCoins = newList,
            transactions = newTransactions
        )
        database.reference
            .child("transactionId")
            .setValue(transaction.transactionId + 1)


        database.reference
            .child("users")
            .child(userId).child("balance")
            .setValue(newBalance)
            .await()
        _coinOperationResult.emit(CoinOperationResult.Success(
            transactionId = transaction.transactionId
        ))
    }

    private suspend fun getTransaction(
        symbol: String,
        count: Double,
        price: Double,
        amount: Double,
        date: Long
    ): TransactionDto {
        val newTransactionNumber = database.reference
            .child("transactionId")
            .get()
            .await()
            .getValue<Int>() ?: 0

        return TransactionDto(
            transactionId = newTransactionNumber,
            userId = auth.currentUser?.uid ?: "",
            type = "buy",
            symbol = symbol,
            count = count,
            price = price,
            amount = amount,
            time = date
        )

    }

    override suspend fun sellCoin(symbol: String, amount: Double) {
        TODO("Not yet implemented")
    }
}