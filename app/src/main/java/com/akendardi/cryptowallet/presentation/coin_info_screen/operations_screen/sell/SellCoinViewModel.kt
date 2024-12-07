package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.sell

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.states.coin_operations.CoinOperationResult
import com.akendardi.cryptowallet.domain.usecase.operations.OperationsUseCase
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.PriceConverter
import com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.components.OperationResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

@HiltViewModel(assistedFactory = SellCoinViewModel.Factory::class)
class SellCoinViewModel @AssistedInject constructor(
    @Assisted private val symbol: String,
    private val operationsUseCase: OperationsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SellCoinScreenState())
    val state: StateFlow<SellCoinScreenState> = _state.asStateFlow()

    init {
        startLoadingInfo()
        subscribeInfoForBuying()
    }

    private fun startLoadingInfo() {
        viewModelScope.launch {
            operationsUseCase.startLoadingInfo(symbol)
        }
    }

    fun sellCoin() {
        viewModelScope.launch {
            operationsUseCase.sellCoin(
                symbol,
                PriceConverter.unFormatPrice(_state.value.countValue)
            )
        }
    }

    private fun subscribeInfoForBuying() {
        viewModelScope.launch {
            operationsUseCase
                .getInfo()
                .collect { result ->
                    when (result) {
                        CoinOperationResult.Error -> {
                            _state.update {
                                it.copy(
                                    operationResult = OperationResult.Error
                                )
                            }
                        }

                        is CoinOperationResult.InfoLoaded -> {
                            updateState(result)
                        }

                        CoinOperationResult.Initial -> {}

                        CoinOperationResult.LoadingInfo -> {
                            _state.update {
                                it.copy(
                                    isFirstLoading = true
                                )
                            }
                        }

                        CoinOperationResult.LoadingOperation -> {
                            _state.update {
                                it.copy(
                                    operationResult = OperationResult.Loading
                                )
                            }
                        }

                        is CoinOperationResult.Success -> {
                            _state.update {
                                it.copy(
                                    operationResult = OperationResult.Success(
                                        transactionId = result.transactionId
                                    )
                                )
                            }
                            operationsUseCase.startLoadingInfo(symbol)
                        }

                    }
                }
        }
    }

    fun changeCountValue(count: String) {
        _state.update {
            it.copy(
                countValue = count.filterIndexed { index, char ->
                    if (char.isDigit()) {
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
        countTotalCount()
    }

    private fun countTotalCount() {
        val amount =
            if (state.value.countValue.isEmpty()) 0.0 else state.value.countValue.toDouble()
        val value = amount * PriceConverter.unFormatPrice(state.value.currentPrice)
        _state.update {
            it.copy(
                totalCount = String.format(locale = Locale.getDefault(), format = "%.2f", value)
            )
        }
    }

    private fun updateError() {
        _state.update {
            it.copy(
                error = if (it.countValue.isEmpty()) {
                    ""
                } else if (it.countValue.toDouble() > PriceConverter.unFormatPrice(it.usersCoinsCount)) {
                    "Недостаточно средств"
                } else {
                    ""
                }
            )
        }
    }

    private fun updateState(result: CoinOperationResult.InfoLoaded) {
        _state.update {
            it.copy(
                isFirstLoading = false,
                name = result.name,
                symbol = symbol,
                imageUrl = result.coinImage,
                currentPrice = PriceConverter.formatPrice(result.currentPrice),
                isCanBuy = result.isAccountVerificated,
                error = "",
                countValue = "",
                usersCoinsCount = "${result.currentCoinsCount} $symbol",
                usersBalanceForCurrentCoin = PriceConverter.formatPrice(result.lockedBalanceForCurrentCoin)
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(symbol: String): SellCoinViewModel
    }
}