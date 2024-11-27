package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.operations.BuyCoinUseCase
import com.akendardi.cryptowallet.domain.states.coin_operations.CoinOperationResult
import com.akendardi.cryptowallet.domain.usecase.operations.LoadInfoForBuyingUseCase
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.PriceConverter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = BuyCoinViewModel.Factory::class)
class BuyCoinViewModel @AssistedInject constructor(
    @Assisted private val symbol: String,
    private val loadInfoForBuyingUseCase: LoadInfoForBuyingUseCase,
    private val buyCoinUseCase: BuyCoinUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BuyCoinScreenState())
    val state: StateFlow<BuyCoinScreenState> = _state.asStateFlow()
    init {
        startLoadingInfo()
        subscribeInfoForBuying()
    }

    private fun startLoadingInfo() {
        viewModelScope.launch {
            loadInfoForBuyingUseCase(symbol)
        }
    }

    fun buyCoin(){
        viewModelScope.launch {
            buyCoinUseCase(symbol, PriceConverter.unFormatPrice(_state.value.amount))
        }
    }

    private fun subscribeInfoForBuying() {
        viewModelScope.launch {
            Log.d("TEST_RESULT", "viewModel: ${loadInfoForBuyingUseCase.getInfoForBuying()}")
            loadInfoForBuyingUseCase
                .getInfoForBuying()
                .collect { result ->
                    Log.d("TEST_RESULT", "$result")
                    when (result) {
                        CoinOperationResult.Error -> {

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
                                    isOperationLoading = true
                                )
                            }
                        }

                        is CoinOperationResult.Success -> {
                            _state.update {
                                it.copy(
                                    operationResult = BuyOperationResult.Success(
                                        transactionId = result.transactionId
                                    ),
                                    isOperationLoading = false
                                )
                            }
                            loadInfoForBuyingUseCase(symbol)
                        }

                    }
                }
        }
    }

    fun changeAmount(amount: String) {
        _state.update {
            it.copy(
                amount = amount.filterIndexed { index, char ->
                    if (char.isDigit()) {
                        true
                    } else if (char == '.') {
                        amount.indexOf('.') == index && index != 0
                    } else {
                        false
                    }
                }.let { filteredAmount ->
                    if (filteredAmount.contains('.')) {
                        val parts = filteredAmount.split('.')
                        val wholePart = parts[0]
                        val fractionalPart = parts.getOrNull(1)?.take(2) ?: ""
                        "$wholePart.$fractionalPart"
                    } else {
                        filteredAmount
                    }
                }
            )
        }
        updateError()
        countTotalCount()
    }

    private fun countTotalCount() {
        val amount = if (state.value.amount.isEmpty()) 0.0 else state.value.amount.toDouble()
        val value = amount / PriceConverter.unFormatPrice(state.value.currentPrice)
        _state.update {
            it.copy(
                totalCount = value.toString()
            )
        }
    }

    private fun updateError() {
        _state.update {
            it.copy(
                error = if (it.amount.isEmpty()) {
                    ""
                } else if (it.amount.toDouble() > PriceConverter.unFormatPrice(it.currentFreeBalance)) {
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
                currentFreeBalance = PriceConverter.formatPrice(result.freeBalance),
                isOperationLoading = false,
                isCanBuy = result.isAccountVerificated,
                error = "",
                amount = "",
                totalCount = "0.0"
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(symbol: String): BuyCoinViewModel
    }
}