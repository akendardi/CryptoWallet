package com.akendardi.cryptowallet.presentation.main.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.usecase.auth.LogOutFromAccountUseCase
import com.akendardi.cryptowallet.domain.usecase.crypto.GetAllCoinsListUseCase
import com.akendardi.cryptowallet.domain.usecase.crypto.GetSearchCoinsListUseCase
import com.akendardi.cryptowallet.domain.usecase.crypto.LoadAllCoinsListUseCase
import com.akendardi.cryptowallet.domain.usecase.crypto.RefreshCoinsListUseCase
import com.akendardi.cryptowallet.domain.usecase.crypto.SearchCoinsUseCase
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.UsersInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val usersInfoUseCase: UsersInfoUseCase,
    private val logOutFromAccountUseCase: LogOutFromAccountUseCase,
    private val loadAllCoinsListUseCase: LoadAllCoinsListUseCase,
    private val getAllCoinsListUseCase: GetAllCoinsListUseCase,
    private val searchCoinsUseCase: SearchCoinsUseCase,
    private val getSearchCoinsListUseCase: GetSearchCoinsListUseCase,
    private val refreshCoinsListUseCase: RefreshCoinsListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenUIState())
    val state: StateFlow<HomeScreenUIState> = _state

    private val queryFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            subscribeUserInfoFlow()
            subscribeCoinsListFlow()
            subscribeSearchInfoFlow()
            refreshUserInfo()
            startSearch()
        }
    }


    private fun subscribeUserInfoFlow() {
        viewModelScope.launch {
            usersInfoUseCase.observeUserInfo().collect { userInfo ->
                _state.update {
                    it.copy(
                        userInfoState = userInfo
                    )
                }
            }
        }
    }

    private fun subscribeSearchInfoFlow() {
        viewModelScope.launch {
            getSearchCoinsListUseCase().collect { listSearchInfo ->
                _state.update {
                    it.copy(
                        searchState = it.searchState.copy(
                            coins = listSearchInfo
                        )
                    )
                }

            }
        }
    }

    fun startRefresh() {
        _state.update {
            it.copy(
                isRefreshing = true
            )
        }
        viewModelScope.launch {
            refreshUserInfo()
            refreshCoinsList()
            _state.update {
                it.copy(
                    isRefreshing = false
                )
            }
        }
    }

    private suspend fun refreshCoinsList() {
        _state.update {
            it.copy(
                coinsListState = it.coinsListState.copy(
                    currentCoinsPage = 1
                )
            )
        }
        refreshCoinsListUseCase()

    }

    fun onSearchQueryChange(query: String) {
        queryFlow.value = query
        _state.update {
            it.copy(
                searchState = it.searchState.copy(
                    query = query
                )
            )
        }
    }

    @OptIn(FlowPreview::class)
    fun startSearch() {
        viewModelScope.launch {
            queryFlow
                .debounce(500)
                .collect { query ->
                    if (query.isNotEmpty() && query.trim().isNotEmpty()) {
                        searchCoinsUseCase(query.trim())
                    }
                }
        }
    }

    fun launchSearchMode() {
        _state.update {
            it.copy(
                screenMode = ScreenMode.SEARCH
            )
        }
    }

    fun exitSearchMode() {
        _state.update {
            it.copy(
                screenMode = ScreenMode.DEFAULT,
                searchState = SearchState()
            )
        }
    }

    private fun subscribeCoinsListFlow() {
        loadCoins()
        viewModelScope.launch {
            getAllCoinsListUseCase()
                .collect { coinInfo ->
                    _state.update {
                        it.copy(
                            coinsListState = it.coinsListState.copy(
                                coinsList = coinInfo
                            )
                        )
                    }
                }
        }
    }

    private suspend fun loadCoinsRequest() {
        if (_state.value.coinsListState.currentCoinsPage == 0) startFirstLoading()
        loadAllCoinsListUseCase(_state.value.coinsListState.currentCoinsPage)
        _state.update {
            it.copy(
                coinsListState = it.coinsListState.copy(
                    isLoadingNextPage = false,
                    currentCoinsPage = it.coinsListState.currentCoinsPage + 1,
                    isFirstLoading = false
                )
            )
        }
    }

    private fun startFirstLoading() {
        _state.update {
            it.copy(
                coinsListState = it.coinsListState.copy(
                    isFirstLoading = true
                )
            )
        }

    }

    fun loadCoins() {
        if (_state.value.coinsListState.isLoadingNextPage) return
        _state.update {
            it.copy(
                coinsListState = it.coinsListState.copy(
                    isLoadingNextPage = true
                )
            )
        }
        viewModelScope.launch {
            loadCoinsRequest()
        }
    }

    fun logOut() {
        viewModelScope.launch {
            logOutFromAccountUseCase()
        }

    }

    private suspend fun refreshUserInfo() {
        usersInfoUseCase()

    }
}