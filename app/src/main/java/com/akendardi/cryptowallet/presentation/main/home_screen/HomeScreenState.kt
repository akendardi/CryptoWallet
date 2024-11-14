package com.akendardi.cryptowallet.presentation.main.home_screen

import com.akendardi.cryptowallet.domain.entity.CoinInfoGeneral
import com.akendardi.cryptowallet.domain.entity.CoinInfoSearch
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
    val totalBalance: String = "82,314.15$",
    val differencePercentage: String = "+87.25%"
)

data class CoinsListState(
    val coinsList: List<CoinInfoGeneral> = listOf(),
    val currentCoinsPage: Int = 0,
    val isLoading: Boolean = false
)
data class SearchState(
    val query: String = "",
    val coins: List<CoinInfoSearch> = listOf()
)

enum class ScreenMode{
    DEFAULT, SEARCH
}
