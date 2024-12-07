package com.akendardi.cryptowallet.data.repositories.transactions

import com.akendardi.cryptowallet.data.internet.dto.user.TransactionDto
import com.akendardi.cryptowallet.domain.repository.UsersTransactionsRepository
import com.akendardi.cryptowallet.domain.states.transactions.TransactionsResult
import com.akendardi.cryptowallet.mapper.transactionDtoToEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersTransactionsRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) : UsersTransactionsRepository {


    private val _transactionsResult: MutableStateFlow<TransactionsResult> =
        MutableStateFlow(TransactionsResult.Initial)
    override val transactionsResult: StateFlow<TransactionsResult> =
        _transactionsResult.asStateFlow()

    override suspend fun loadUsersTransactions() {
        try {
            _transactionsResult.emit(TransactionsResult.Loading)
            val userId = auth.currentUser?.uid ?: ""
            val balance = database.reference
                .child("users")
                .child(userId)
                .child("balance")
                .child("transactions")
                .get().await()

            val transactions = (balance.getValue<List<TransactionDto>>()
                ?: listOf()).map { transactionDtoToEntity(it) }.sortedByDescending { it.time }


            _transactionsResult.emit(TransactionsResult.Success(transactions))
        } catch (e: Exception) {
            _transactionsResult.emit(TransactionsResult.Error)
        }


    }
}