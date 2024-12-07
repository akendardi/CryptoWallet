package com.akendardi.cryptowallet.data.repositories.transactions

import com.akendardi.cryptowallet.data.internet.dto.user.BalanceInfoDto
import com.akendardi.cryptowallet.domain.repository.CoinsTransactionsRepository
import com.akendardi.cryptowallet.domain.states.transactions.TransactionsResult
import com.akendardi.cryptowallet.mapper.transactionDtoToEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CoinsTransactionsRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) : CoinsTransactionsRepository {

    private val _transactionsResult =
        MutableStateFlow<TransactionsResult>(TransactionsResult.Initial)
    override val transactionsResult = _transactionsResult.asStateFlow()

    override suspend fun loadCoinsTransactions(symbol: String) {
        _transactionsResult.emit(TransactionsResult.Loading)
        try {
            val userId = auth.currentUser?.uid ?: ""
            val balanceDto = database.reference
                .child("users")
                .child(userId)
                .child("balance")
                .get()
                .await()

            val balance = balanceDto.getValue(BalanceInfoDto::class.java)
            val transactions = balance?.transactions ?: listOf()

            val coinsTransactions =
                transactions.filter { it.symbol == symbol }.map { transactionDtoToEntity(it) }
                    .sortedByDescending { it.time }

            _transactionsResult.emit(TransactionsResult.Success(coinsTransactions))

        } catch (e: Exception) {
            _transactionsResult.emit(TransactionsResult.Error)
        }
    }
}