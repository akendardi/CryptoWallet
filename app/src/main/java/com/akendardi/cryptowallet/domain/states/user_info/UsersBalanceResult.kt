package com.akendardi.cryptowallet.domain.states.user_info

import com.akendardi.cryptowallet.domain.entity.user_info.balance.UsersBalance

sealed class UsersBalanceResult {

    data object Initial : UsersBalanceResult()

    data object Loading : UsersBalanceResult()

    data class Success(val usersBalance: UsersBalance) : UsersBalanceResult()

    data object Error : UsersBalanceResult()
}