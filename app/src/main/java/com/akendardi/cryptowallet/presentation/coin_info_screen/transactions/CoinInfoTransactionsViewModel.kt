package com.akendardi.cryptowallet.presentation.coin_info_screen.transactions

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = CoinInfoTransactionsViewModel.Factory::class)
class CoinInfoTransactionsViewModel @AssistedInject constructor(
    @Assisted private val symbol: String
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(symbol: String): CoinInfoTransactionsViewModel
    }
}