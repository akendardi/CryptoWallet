package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.buy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.states.coin_operations.CoinOperationResult
import com.akendardi.cryptowallet.domain.usecase.operations.LoadInfoForBuyingUseCase
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
    private val loadInfoForBuyingUseCase: LoadInfoForBuyingUseCase
) : ViewModel() {

    init {
        startLoadingInfo()
        subscribeInfoForBuying()

    }

    private val _state = MutableStateFlow(BuyCoinScreenState())
    val state: StateFlow<BuyCoinScreenState> = _state.asStateFlow()

    private fun startLoadingInfo() {
        viewModelScope.launch {
            loadInfoForBuyingUseCase(symbol)
        }
    }

    private fun subscribeInfoForBuying() {
        viewModelScope.launch {
            loadInfoForBuyingUseCase
                .getInfoForBuying()
                .collect { result ->
                when (result) {
                    CoinOperationResult.Error -> {

                    }

                    is CoinOperationResult.InfoLoaded -> {
                        updateState(result)
                    }

                    CoinOperationResult.Initial -> {

                    }

                    CoinOperationResult.LoadingInfo -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    CoinOperationResult.LoadingOperation -> {

                    }

                    is CoinOperationResult.Success -> {

                    }

                }
            }
        }
    }

    private fun updateState(result: CoinOperationResult.InfoLoaded) {
        _state.update {
            it.copy(
                isLoading = false,
                name = result.name,
                symbol = symbol,
                imageUrl = result.coinImage,
                currentPrice = result.currentPrice.toString(),
                currentFreeBalance = result.freeBalance.toString(),
                error = ""
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(symbol: String): BuyCoinViewModel
    }
}