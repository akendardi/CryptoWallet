package com.akendardi.cryptowallet.presentation.main.transactions_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.states.transactions.TransactionsResult
import com.akendardi.cryptowallet.domain.usecase.transactions.UsersTransactionUseCase
import com.akendardi.cryptowallet.mapper.transactionToUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsScreenViewModel @Inject constructor(
    private val usersTransactionUseCase: UsersTransactionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UsersTransactionsScreenState())
    val state = _state.asStateFlow()

    private var isLoadingWas = false

    fun loadData() {
        viewModelScope.launch {
            usersTransactionUseCase.loadUsersTransactions()
        }
    }

    private fun subscribeResult() {
        viewModelScope.launch {
            usersTransactionUseCase.getUserTransactions().collect { result ->
                when (result) {
                    TransactionsResult.Error -> {
                        _state.update {
                            it.copy(
                                isError = true
                            )
                        }
                    }

                    TransactionsResult.Initial -> {}

                    TransactionsResult.Loading -> {
                        if (isLoadingWas) {
                            _state.update {
                                it.copy(
                                    isRefreshing = true
                                )
                            }
                        } else {
                            _state.update {
                                it.copy(
                                    isFirstLoading = true
                                )
                            }
                            isLoadingWas = true
                        }

                    }

                    is TransactionsResult.Success -> {
                        _state.update {
                            it.copy(
                                isFirstLoading = false,
                                isError = false,
                                isRefreshing = false,
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
        subscribeResult()
    }
}