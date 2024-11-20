package com.akendardi.cryptowallet.presentation.main.home_screen

import com.akendardi.cryptowallet.domain.entity.coin_info_general.CoinInfoGeneral
import com.akendardi.cryptowallet.domain.entity.coin_info_search.CoinInfoSearch
import com.akendardi.cryptowallet.domain.entity.user_info.UserInfo

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
    val isFirstLoading: Boolean = false,
    val isLoadingNextPage: Boolean = false
)
data class SearchState(
    val query: String = "",
    val coins: List<CoinInfoSearch> = listOf()
)

enum class ScreenMode{
    DEFAULT, SEARCH
}
