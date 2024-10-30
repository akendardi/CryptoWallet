package com.akendardi.cryptowallet.presentation.main.home_screen

import com.akendardi.cryptowallet.domain.entity.CoinInfo
import com.akendardi.cryptowallet.domain.entity.SearchCoinInfo
import com.akendardi.cryptowallet.domain.entity.UserInfo

data class HomeScreenUIState(
    val userInfoState: UserInfo = UserInfo(),
    val balanceInfo: BalanceInfo = BalanceInfo(),
    val coinsListState: CoinsListState = CoinsListState(),
    val screenMode: ScreenMode = ScreenMode.DEFAULT,
    val searchState: SearchState = SearchState()
)

data class BalanceInfo(
    val balance: String = "0.0$",
    val difference: String = "0",
    val isPositiveDifference: Boolean = true
)

data class CoinsListState(
    val coinsList: List<CoinInfo> = listOf(),
    val currentCoinsPage: Int = 0,
    val isLoading: Boolean = false
)
data class SearchState(
    val query: String = "",
    val coins: List<SearchCoinInfo> = listOf()
)

enum class ScreenMode{
    DEFAULT, SEARCH
}
