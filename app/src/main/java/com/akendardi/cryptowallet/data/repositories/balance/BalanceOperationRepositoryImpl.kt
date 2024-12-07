package com.akendardi.cryptowallet.data.repositories.balance

import com.akendardi.cryptowallet.data.internet.dto.user.BalanceInfoDto
import com.akendardi.cryptowallet.domain.repository.BalanceOperationRepository
import com.akendardi.cryptowallet.domain.states.balance_operations.BalanceOperationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BalanceOperationRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) : BalanceOperationRepository {

    private val _currentFreeBalance =
        MutableStateFlow<BalanceOperationResult>(BalanceOperationResult.Initial)
    override val currentFreeBalance = _currentFreeBalance.asStateFlow()
    override suspend fun resetInfo() {
        _currentFreeBalance.emit(BalanceOperationResult.Initial)
    }


    override suspend fun loadFreeBalance() {
        _currentFreeBalance.emit(BalanceOperationResult.LoadingBalance)
        val balance = getBalance()
        _currentFreeBalance.emit(BalanceOperationResult.BalanceLoaded(balance.freeBalance))
    }

    private suspend fun getBalance(): BalanceInfoDto {
        val userId = auth.currentUser?.uid ?: ""
        return database.reference
            .child("users")
            .child(userId)
            .child("balance")
            .get()
            .await()
            .getValue(BalanceInfoDto::class.java) ?: BalanceInfoDto()
    }

    override suspend fun addToBalance(amount: Double) {
        try {
            _currentFreeBalance.emit(BalanceOperationResult.LoadingOperation)
            val currentBalance = getBalance()
            val newFreeBalance = currentBalance.freeBalance + amount
            val newBalance = currentBalance.copy(
                freeBalance = newFreeBalance
            )
            setNewBalance(newBalance)
        } catch (e: Exception) {
            _currentFreeBalance.emit(BalanceOperationResult.Error)
        }
    }

    private suspend fun setNewBalance(balanceInfoDto: BalanceInfoDto) {
        val userId = auth.currentUser?.uid ?: ""
        database.reference
            .child("users")
            .child(userId)
            .child("balance")
            .setValue(balanceInfoDto)
            .await()
        _currentFreeBalance.emit(BalanceOperationResult.Success)
    }

    override suspend fun removeFromBalance(amount: Double) {
        try {
            _currentFreeBalance.emit(BalanceOperationResult.LoadingOperation)
            val currentBalance = getBalance()
            val newFreeBalance = currentBalance.freeBalance - amount
            if (newFreeBalance < 0) {
                return
            }
            val newBalance = currentBalance.copy(
                freeBalance = newFreeBalance
            )
            setNewBalance(newBalance)
        } catch (e: Exception) {
            _currentFreeBalance.emit(BalanceOperationResult.Error)
        }
    }
}