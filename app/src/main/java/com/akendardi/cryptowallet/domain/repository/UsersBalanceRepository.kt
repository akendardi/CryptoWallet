package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.states.user_info.UsersBalanceResult
import kotlinx.coroutines.flow.StateFlow

interface UsersBalanceRepository {

    val usersBalanceResult: StateFlow<UsersBalanceResult>

    suspend fun  loadUsersBalance()
}