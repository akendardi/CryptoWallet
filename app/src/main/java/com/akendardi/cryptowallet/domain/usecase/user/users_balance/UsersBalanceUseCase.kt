package com.akendardi.cryptowallet.domain.usecase.user.users_balance

import com.akendardi.cryptowallet.domain.repository.UsersBalanceRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UsersBalanceUseCase @Inject constructor(
    private val repository: UsersBalanceRepository
) {
    suspend fun loadUsersBalance() = repository.loadUsersBalance()

    fun getUsersBalanceResult() = repository.usersBalanceResult
}