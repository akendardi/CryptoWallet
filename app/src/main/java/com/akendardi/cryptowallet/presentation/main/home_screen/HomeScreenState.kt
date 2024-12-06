package com.akendardi.cryptowallet.presentation.main.home_screen

import android.net.Uri
import com.akendardi.cryptowallet.domain.entity.coin_info_general.CoinInfoGeneral
import com.akendardi.cryptowallet.domain.entity.coin_info_search.CoinInfoSearch
import com.akendardi.cryptowallet.presentation.main.wallet_screen.BalanceUI

data class HomeScreenUIState(
    val generalUserInfoState: UserInfoState = UserInfoState(),
    val userBalanceState: BalanceUI = BalanceUI(),
    val isBalanceLoading: Boolean = false,
    val coinsListState: CoinsListState = CoinsListState(),
    val screenMode: ScreenMode = ScreenMode.DEFAULT,
    val searchState: SearchState = SearchState(),
    val isRefreshing: Boolean = false,
    val error: String = ""
)

data class UserInfoState(
    val userName: String = "",
    val profileUri: Uri = Uri.parse(""),
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
