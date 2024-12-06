package com.akendardi.cryptowallet.presentation.coin_info_screen.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.states.transactions.TransactionsResult
import com.akendardi.cryptowallet.domain.usecase.transactions.CoinsTransactionUseCase
import com.akendardi.cryptowallet.mapper.transactionToUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = CoinInfoTransactionsViewModel.Factory::class)
class CoinInfoTransactionsViewModel @AssistedInject constructor(
    @Assisted private val symbol: String,
    private val coinsTransactionUseCase: CoinsTransactionUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<CoinsInfoTransactionsScreenState> =
        MutableStateFlow(CoinsInfoTransactionsScreenState())
    val state = _state.asStateFlow()

    private var isFirstLoadingWas: Boolean = false

    fun loadData() {
        viewModelScope.launch {
            coinsTransactionUseCase.loadCoinsTransactions(symbol)
        }
    }

    private fun subscribe(){
        viewModelScope.launch {
            coinsTransactionUseCase.getCoinsTransactionsResult().collect { result ->
                when (result) {
                    TransactionsResult.Error -> {
                        _state.update {
                            it.copy(
                                isError = true
                            )
                        }
                    }

                    TransactionsResult.Initial -> {

                    }

                    TransactionsResult.Loading -> {
                        if (isFirstLoadingWas) {
                            _state.update {
                                it.copy(
                                    isRefreshing = true
                                )
                            }
                        } else {
                            isFirstLoadingWas = true
                            _state.update {
                                it.copy(
                                    isFirstLoading = true
                                )
                            }
                        }

                    }

                    is TransactionsResult.Success -> {
                        _state.update {
                            it.copy(
                                isError = false,
                                isRefreshing = false,
                                isFirstLoading = false,
                                transactions = result.transactions.map { transaction ->
                                    transactionToUi(
                                        transaction
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
        subscribe()
    }

    @AssistedFactory
    interface Factory {
        fun create(symbol: String): CoinInfoTransactionsViewModel
    }
}