package com.akendardi.cryptowallet.presentation.main.wallet_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.states.user_info.UsersBalanceResult
import com.akendardi.cryptowallet.domain.usecase.user.users_balance.UsersBalanceUseCase
import com.akendardi.cryptowallet.mapper.balanceToUi
import com.akendardi.cryptowallet.mapper.purchasedCoinToUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val balanceUseCase: UsersBalanceUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WalletScreenState())
    val state = _state.asStateFlow()

    private var isLoadingWas = false

    fun loadData() {
        viewModelScope.launch {
            balanceUseCase.loadUsersBalance()
        }
    }

    fun changeScreen(screen: CurrentWalletScreen) {
        _state.update {
            it.copy(
                currentWalletScreen = screen
            )
        }

    }

    private fun subscribeData() {
        viewModelScope.launch {
            balanceUseCase.getUsersBalanceResult().collect { result ->
                when (result) {
                    UsersBalanceResult.Error -> {
                        _state.update {
                            it.copy(
                                isError = true
                            )
                        }
                    }

                    UsersBalanceResult.Initial -> {

                    }

                    UsersBalanceResult.Loading -> {
                        if (isLoadingWas) {
                            _state.update {
                                it.copy(
                                    isRefreshing = true
                                )
                            }
                        } else {
                            isLoadingWas = true
                            _state.update {
                                it.copy(
                                    isFirstLoading = true
                                )
                            }
                        }
                    }

                    is UsersBalanceResult.Success -> {
                        val balanceUi = balanceToUi(result.usersBalance)
                        _state.update {
                            it.copy(
                                isFirstLoading = false,
                                isError = false,
                                isRefreshing = false,
                                balance = balanceUi,
                                purchasedCoins = result.usersBalance.purchasedCoins.map { coin ->
                                    purchasedCoinToUI(
                                        coin
                                    )
                                }
                            )
                        }
                    }

                }
            }
        }
    }

    init {
        loadData()
        subscribeData()

    }
}