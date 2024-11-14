package com.akendardi.cryptowallet.presentation.main.coin_info_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.states.crypto.CryptoInfoLoadingResult
import com.akendardi.cryptowallet.domain.usecase.crypto.LoadCoinInfoUseCase
import com.akendardi.cryptowallet.mapper.formatUnixTimestampsForDays
import com.akendardi.cryptowallet.mapper.formatUnixTimestampsForHours
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
            subscribes()
        }
    }

    private val _state =
        MutableStateFlow(CoinInfoScreenState(coinInfoState = CoinInfoState()))
    val state = _state.asStateFlow()

    private suspend fun subscribes() {
        loadCoinInfoUseCase.subscribeCoinInfo().collect { result ->
            when (result) {
                CryptoInfoLoadingResult.Error -> {

                }

                CryptoInfoLoadingResult.Initial -> {

                }

                CryptoInfoLoadingResult.Loading -> {

                }

                is CryptoInfoLoadingResult.Success -> {
                    updateState(result)
                }

            }
        }

    }

    private fun List<List<String>>.selectSixDates(): List<List<String>> {
        if (this.size <= 6) return this

        val step = (this.size - 1) / 5
        return List(6) { i -> this[i * step] }
    }

    private fun List<List<String>>.selectSixHours(): List<List<String>> {
        if (this.size <= 6) return this

        val step = (this.size - 1) / 5
        return List(6) { i ->
            if (i == 5) this.last() else this[i * step]
        }
    }

    private fun clearHoursDates(hours: List<List<String>>): List<List<String>> {
        val days = mutableSetOf<String>()
        val list = mutableListOf<List<String>>()
        for (hour in hours) {
            if (hour[1] !in days) {
                list.add(hour)
                days.add(hour[1])
            } else {
                list.add(listOf(hour[0]))
            }
        }
        return list
    }

    private fun updateState(result: CryptoInfoLoadingResult.Success) {
        val screenTimesDays =
            formatUnixTimestampsForDays(result.coinInfo.pricesDay.map { it.time }).selectSixDates()
        val screenTimesHours =
            formatUnixTimestampsForHours(result.coinInfo.pricesHour.map { it.time }).selectSixHours()
        val clearHours = clearHoursDates(screenTimesHours)



        _state.value = CoinInfoScreenState(
            coinInfoState = CoinInfoState(
                symbol = result.coinInfo.symbol,
                name = result.coinInfo.name,
                imageUrl = result.coinInfo.imageUrl,
                price = result.coinInfo.currentPrice
            ),
            hourGraphicState = GraphicPriceState(
                datesOnScreen = clearHours,
                prices = result.coinInfo.pricesHour.map { it.price }
            ),
            dayGraphicState = GraphicPriceState(
                datesOnScreen = screenTimesDays,
                prices = result.coinInfo.pricesDay.map { it.price }
            )
        )
    }


    @AssistedFactory
    interface Factory {
        fun create(symbol: String): CoinInfoViewModel
    }
}