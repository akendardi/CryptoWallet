package com.akendardi.cryptowallet.presentation.main.home_screen

import com.akendardi.cryptowallet.domain.entity.UserInfo

data class HomeScreenUIState(
    val userInfoState: UserInfo = UserInfo(),
    val balanceInfo: BalanceInfo = BalanceInfo()
)

data class BalanceInfo(
    val balance: String = "0.0$",
    val difference: String = "0",
    val isPositiveDifference: Boolean = true
)
