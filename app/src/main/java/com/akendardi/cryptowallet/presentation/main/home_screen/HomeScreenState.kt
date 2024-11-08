package com.akendardi.cryptowallet.presentation.main.home_screen

import com.akendardi.cryptowallet.domain.entity.CoinInfo
import com.akendardi.cryptowallet.domain.entity.SearchCoinInfo
import com.akendardi.cryptowallet.domain.entity.UserInfo

data class HomeScreenUIState(
    val userInfoState: UserInfo = UserInfo(),
    val userBalanceState: BalanceInfo = BalanceInfo(),
    val coinsListState: CoinsListState = CoinsListState(),
    val screenMode: ScreenMode = ScreenMode.DEFAULT,
    val searchState: SearchState = SearchState(),
    val isRefreshing: Boolean = false
)

data class BalanceInfo(
    val totalBalance: String = "0.0$",
    val differencePercentage: String = "0"
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
