package com.akendardi.cryptowallet.presentation.main.coin_info_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.usecase.crypto.LoadCoinInfoUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel(assistedFactory = CoinInfoViewModel.Factory::class)
class CoinInfoViewModel @AssistedInject constructor(
    @Assisted val symbol: String,
    private val loadCoinInfoUseCase: LoadCoinInfoUseCase
) : ViewModel() {


    init {
        viewModelScope.launch {
            loadCoinInfoUseCase(symbol)
        }

    }

    private val _state =
        MutableStateFlow(CoinInfoScreenState(coinInfoState = CoinInfoState(symbol)))
    val state = _state.asStateFlow()



    @AssistedFactory
    interface Factory {
        fun create(symbol: String): CoinInfoViewModel
    }
}