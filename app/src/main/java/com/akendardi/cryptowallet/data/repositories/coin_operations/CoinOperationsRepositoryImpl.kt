package com.akendardi.cryptowallet.data.repositories.coin_operations

import com.akendardi.cryptowallet.data.internet.api.AssetsCoinsApiService
import com.akendardi.cryptowallet.data.internet.dto.user.BalanceInfoDto
import com.akendardi.cryptowallet.data.internet.dto.user.PurchasedCoinDto
import com.akendardi.cryptowallet.data.internet.dto.user.TransactionDto
import com.akendardi.cryptowallet.domain.repository.CoinOperationsRepository
import com.akendardi.cryptowallet.domain.states.coin_operations.CoinOperationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
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

    private suspend fun getBalance(): BalanceInfoDto? {
        val userId = auth.currentUser?.uid ?: ""
        val snapshot = database.reference
            .child("users")
            .child(userId)
            .child("balance")
            .get()
            .await()

        return snapshot.getValue(BalanceInfoDto::class.java)
    }


    override suspend fun getInfoForBuying(symbol: String) {
        _coinOperationResult.emit(CoinOperationResult.LoadingInfo)

        val balance = getBalance()
        if (balance == null) {
            _coinOperationResult.emit(CoinOperationResult.Error)
            return
        }

        val freeBalance = balance.freeBalance ?: 0.0
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

    private suspend fun loadNewCoinsInformationAndAddToPurchasedCoins(
        symbol: String,
        price: Double,
        count: Double,
        balance: BalanceInfoDto
    ): List<PurchasedCoinDto> {


        val oldListCoins = balance.purchasedCoins ?: listOf()

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

        return oldListCoins.toMutableList().apply {
            remove(currentPurchasedCoin)
            add(newPurchasedCoin)
        }
    }

    override suspend fun buyCoin(symbol: String, amount: Double) {
        _coinOperationResult.emit(CoinOperationResult.LoadingOperation)

        val coinDto = apiService.loadDetailCoinInfo(symbol).data.values.first()
        val price = coinDto.price
        val count = amount / price

        val balance = getBalance()
        if (balance == null) {
            _coinOperationResult.emit(CoinOperationResult.Error)
            return
        }
        if ((balance.freeBalance ?: 0.0) < amount) {
            _coinOperationResult.emit(CoinOperationResult.Error)
            return
        }

        val userId = auth.currentUser?.uid ?: ""

        val newCoinsList = loadNewCoinsInformationAndAddToPurchasedCoins(
            symbol = symbol,
            price = price,
            count = count,
            balance = balance
        )


        val transaction = getTransaction(
            symbol = symbol,
            count = count,
            price = price,
            amount = amount,
            date = System.currentTimeMillis()
        )

        val newTransactions = getNewTransactionsList(userId, transaction)


        val newBalance = balance.copy(
            lockedBalance = (balance.lockedBalance ?: 0.0) + amount,
            freeBalance = (balance.freeBalance ?: 0.0) - amount,
            totalBalance = (balance.totalBalance ?: 0.0),
            purchasedCoins = newCoinsList,
            transactions = newTransactions
        )

        uploadNewBalance(
            userId = userId,
            balance = newBalance
        )
        _coinOperationResult.emit(
            CoinOperationResult.Success(
                transactionId = transaction.transactionId
            )
        )
    }

    private suspend fun uploadNewBalance(
        userId: String,
        balance: BalanceInfoDto
    ) {
        database.reference
            .child("users")
            .child(userId).child("balance")
            .setValue(balance)
            .await()
    }

    private suspend fun getNewTransactionsList(
        userId: String,
        transaction: TransactionDto
    ): List<TransactionDto> {
        val oldTransactions = database.reference
            .child("users")
            .child(userId)
            .child("balance")
            .child("transactions")
            .get()
            .await()
            .getValue<List<TransactionDto>>() ?: listOf()

        database.reference
            .child("transactionId")
            .setValue(transaction.transactionId + 1)

        return oldTransactions.toMutableList().apply {
            add(transaction)
        }
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