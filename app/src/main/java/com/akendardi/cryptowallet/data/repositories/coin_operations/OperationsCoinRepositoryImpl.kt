package com.akendardi.cryptowallet.data.repositories.coin_operations

import com.akendardi.cryptowallet.data.internet.api.AssetsCoinsApiService
import com.akendardi.cryptowallet.data.internet.dto.user.BalanceInfoDto
import com.akendardi.cryptowallet.data.internet.dto.user.PurchasedCoinDto
import com.akendardi.cryptowallet.data.internet.dto.user.TransactionDto
import com.akendardi.cryptowallet.domain.repository.OperationsCoinRepository
import com.akendardi.cryptowallet.domain.states.coin_operations.CoinOperationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OperationsCoinRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val apiService: AssetsCoinsApiService
) : OperationsCoinRepository {

    private val _operationResult =
        MutableStateFlow<CoinOperationResult>(CoinOperationResult.Initial)
    override val operationResult = _operationResult.asStateFlow()

    private suspend fun getBalance(): BalanceInfoDto? {
        val userId = auth.currentUser?.uid ?: ""
        val snapshot =
            database.reference.child("users").child(userId).child("balance").get().await()

        return snapshot.getValue(BalanceInfoDto::class.java)
    }


    override suspend fun getInfo(symbol: String) {
        _operationResult.emit(CoinOperationResult.LoadingInfo)

        val balance = getBalance()
        if (balance == null) {
            _operationResult.emit(CoinOperationResult.Error)
            return
        }

        val freeBalance = balance.freeBalance
        val coinDto = apiService.loadDetailCoinInfo(symbol).data.values.first()

        val price = coinDto.price
        val name = coinDto.name
        val coinImage = coinDto.logoUrl
        val isAccountVerificated = auth.currentUser?.isEmailVerified ?: false

        val currentPurchasedCoin = balance.purchasedCoins.find { it.symbol == symbol }
        val lockedBalanceForCoin = currentPurchasedCoin?.count?.times(coinDto.price) ?: 0.0

        val result = CoinOperationResult.InfoLoaded(
            name = name,
            freeBalance = freeBalance,
            currentPrice = price,
            coinImage = coinImage,
            isAccountVerificated = isAccountVerificated,
            lockedBalanceForCurrentCoin = lockedBalanceForCoin,
            currentCoinsCount = currentPurchasedCoin?.count ?: 0.0
        )

        _operationResult.emit(result)

    }


    private fun loadNewCoinsInformationAndChangePurchasedCoins(
        symbol: String,
        price: Double,
        count: Double,
        balance: BalanceInfoDto,
        type: String,
        imageUrl: String,
        name: String
    ): List<PurchasedCoinDto> {
        val oldListCoins = balance.purchasedCoins

        val currentPurchasedCoin = oldListCoins.find { it.symbol == symbol } ?: PurchasedCoinDto()

        val newPurchasedCoin: PurchasedCoinDto

        if (type == "buy") {
            val sumPrice =
                currentPurchasedCoin.buyPrice * currentPurchasedCoin.count + price * count
            val sumCount = currentPurchasedCoin.count + count
            val newPrice = sumPrice / sumCount
            newPurchasedCoin = PurchasedCoinDto(
                count = sumCount,
                buyPrice = newPrice,
                symbol = symbol,
                imageUrl = imageUrl,
                name = name
            )
        } else {
            val sumPrice =
                currentPurchasedCoin.buyPrice * currentPurchasedCoin.count - price * count
            val sumCount = currentPurchasedCoin.count - count
            val newPrice = sumPrice / sumCount
            newPurchasedCoin = PurchasedCoinDto(
                count = sumCount, buyPrice = newPrice, symbol = symbol, imageUrl = imageUrl
            )
        }

        return oldListCoins.toMutableList().apply {
            remove(currentPurchasedCoin)
            if (newPurchasedCoin.count != 0.0) {
                add(newPurchasedCoin)
            }
        }
    }

    override suspend fun buyCoin(symbol: String, count: Double) {
        _operationResult.emit(CoinOperationResult.LoadingOperation)

        val coinDto = apiService.loadDetailCoinInfo(symbol).data.values.first()
        val price = coinDto.price
        val amount = price * count

        val balance = getBalance()
        if (balance == null) {
            _operationResult.emit(CoinOperationResult.Error)
            return
        }
        if (balance.freeBalance < count) {
            _operationResult.emit(CoinOperationResult.Error)
            return
        }

        val userId = auth.currentUser?.uid ?: ""

        val newCoinsList = loadNewCoinsInformationAndChangePurchasedCoins(
            symbol = symbol,
            price = price,
            count = count,
            balance = balance,
            type = "buy",
            imageUrl = coinDto.logoUrl,
            name = coinDto.name
        )


        val transaction = getTransaction(
            symbol = symbol,
            count = count,
            price = price,
            amount = amount,
            date = System.currentTimeMillis(),
            type = TransactionDto.TYPE_BUY,
            coinImage = coinDto.logoUrl
        )

        val newTransactions = getNewTransactionsList(userId, transaction)


        val newBalance = balance.copy(
            freeBalance = balance.freeBalance - amount,
            purchasedCoins = newCoinsList,
            transactions = newTransactions
        )

        uploadNewBalance(
            userId = userId, balance = newBalance
        )
        _operationResult.emit(
            CoinOperationResult.Success(
                transactionId = transaction.transactionId
            )
        )
    }

    override suspend fun sellCoin(symbol: String, count: Double) {
        _operationResult.emit(CoinOperationResult.LoadingOperation)

        val coinDto = apiService.loadDetailCoinInfo(symbol).data.values.first()
        val price = coinDto.price
        val amount = count * price

        val balance = getBalance()
        if (balance == null) {
            _operationResult.emit(CoinOperationResult.Error)
            return
        }
        val currentCoinBalanceItem =
            balance.purchasedCoins.find { it.symbol == symbol } ?: PurchasedCoinDto()
        if (currentCoinBalanceItem.count < count) {
            _operationResult.emit(CoinOperationResult.Error)
            return
        }

        val userId = auth.currentUser?.uid ?: ""

        val newCoinsList = loadNewCoinsInformationAndChangePurchasedCoins(
            symbol = symbol,
            price = price,
            count = count,
            balance = balance,
            type = "sell",
            imageUrl = coinDto.logoUrl,
            name = coinDto.name
        )


        val transaction = getTransaction(
            symbol = symbol,
            count = count,
            price = price,
            amount = amount,
            date = System.currentTimeMillis(),
            type = TransactionDto.TYPE_SELL,
            coinImage = coinDto.logoUrl
        )

        val newTransactions = getNewTransactionsList(userId, transaction)


        val newBalance = balance.copy(
            freeBalance = balance.freeBalance + amount,
            purchasedCoins = newCoinsList,
            transactions = newTransactions
        )

        uploadNewBalance(
            userId = userId, balance = newBalance
        )
        _operationResult.emit(
            CoinOperationResult.Success(
                transactionId = transaction.transactionId
            )
        )
    }

    private suspend fun uploadNewBalance(
        userId: String, balance: BalanceInfoDto
    ) {
        database.reference.child("users").child(userId).child("balance").setValue(balance).await()
    }

    private suspend fun getNewTransactionsList(
        userId: String, transaction: TransactionDto
    ): List<TransactionDto> {
        val oldTransactions =
            database.reference.child("users").child(userId).child("balance").child("transactions")
                .get().await().getValue<List<TransactionDto>>() ?: listOf()

        database.reference.child("transactionId").setValue(transaction.transactionId + 1)

        return oldTransactions.toMutableList().apply {
            add(transaction)
        }
    }

    private suspend fun getTransaction(
        symbol: String, count: Double, price: Double, amount: Double, date: Long, type: String,
        coinImage: String
    ): TransactionDto {
        val newTransactionNumber =
            database.reference.child("transactionId").get().await().getValue<Int>() ?: 0

        return TransactionDto(
            transactionId = newTransactionNumber,
            userId = auth.currentUser?.uid ?: "",
            type = type,
            symbol = symbol,
            imageUrl = coinImage,
            count = count,
            price = price,
            amount = amount,
            time = date
        )

    }

}