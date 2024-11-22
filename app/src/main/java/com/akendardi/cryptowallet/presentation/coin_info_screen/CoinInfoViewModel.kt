package com.akendardi.cryptowallet.presentation.coin_info_screen

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


@HiltViewModel(assistedFactory = CoinInfoViewModel.Factory::class)
class CoinInfoViewModel @AssistedInject constructor(
    @Assisted("symbol") val symbol: String,
    @Assisted("name") val name: String
) : ViewModel() {


    private val _state =
        MutableStateFlow(
            CoinInfoScreenState(
                coinInfoState = CoinInfoState(
                    symbol = symbol,
                    name = name
                )
            )
        )
    val state = _state.asStateFlow()

    fun updateTab(tab: Int) {
        if (tab == 0) {
            _state.update {
                it.copy(
                    currentScreen = CurrentCoinInfoScreen.INFORMATION
                )
            }
        } else {
            _state.update {
                it.copy(
                    currentScreen = CurrentCoinInfoScreen.TRANSACTIONS
                )
            }
        }
    }


    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("symbol") symbol: String,
            @Assisted("name") name: String
        ): CoinInfoViewModel
    }
}