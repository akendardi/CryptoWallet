package com.akendardi.cryptowallet.presentation.main.wallet_screen.components.remove_from_balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.states.balance_operations.BalanceOperationResult
import com.akendardi.cryptowallet.domain.usecase.user.users_balance.BalanceOperationsUseCase
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.PriceConverter
import com.akendardi.cryptowallet.presentation.main.wallet_screen.components.BalanceOperationUiResult
import com.akendardi.cryptowallet.presentation.main.wallet_screen.components.BalanceOperationsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlertRemoveFromBalanceViewModel @Inject constructor(
    private val balanceOperationsUseCase: BalanceOperationsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BalanceOperationsState())
    val state = _state.asStateFlow()

    init {
        subscribeOperationsResult()
    }

    fun loadBalance() {
        viewModelScope.launch {
            balanceOperationsUseCase.loadBalance()
        }
    }


    fun changeCount(count: String) {
        _state.update {
            it.copy(
                amount = count.filterIndexed { index, char ->
                    if ((char.isDigit() && (!count.contains('.') || (index - count.indexOf('.')) < 3))) {
                        true
                    } else if (char == '.') {
                        count.indexOf('.') == index && index != 0
                    } else {
                        false
                    }
                }
            )
        }
        updateError()
    }

    private fun subscribeOperationsResult() {
        viewModelScope.launch {
            balanceOperationsUseCase.getBalanceOperationsResult().collect { result ->
                when (result) {
                    is BalanceOperationResult.BalanceLoaded -> {
                        _state.update {
                            it.copy(
                                currentBalance = PriceConverter.formatPrice(result.freeBalance)
                            )
                        }
                    }

                    BalanceOperationResult.Error -> {
                        _state.update {
                            it.copy(
                                result = BalanceOperationUiResult.Error
                            )
                        }
                    }

                    BalanceOperationResult.Initial -> {
                    }

                    BalanceOperationResult.LoadingBalance -> {
                    }

                    BalanceOperationResult.LoadingOperation -> {
                        _state.update {
                            it.copy(
                                result = BalanceOperationUiResult.Loading
                            )
                        }
                    }

                    BalanceOperationResult.Success -> {
                        _state.update {
                            it.copy(
                                result = BalanceOperationUiResult.Success
                            )
                        }
                    }

                }
            }
        }

    }

    fun removeFromBalance() {
        viewModelScope.launch {
            balanceOperationsUseCase.removeFromBalance(state.value.amount.toDouble())
        }
    }

    fun resetInfo() {
        viewModelScope.launch {
            balanceOperationsUseCase.resetInfo()
        }
        _state.update {
            BalanceOperationsState()
        }
    }

    private fun updateError() {
        _state.update {
            it.copy(
                error = if (it.amount.isEmpty()) {
                    ""
                } else if (it.amount.toDouble() > PriceConverter.unFormatPrice(it.currentBalance)) {
                    "Нельзя вывести больше свободной суммы"
                } else {
                    ""
                }
            )
        }
    }
}