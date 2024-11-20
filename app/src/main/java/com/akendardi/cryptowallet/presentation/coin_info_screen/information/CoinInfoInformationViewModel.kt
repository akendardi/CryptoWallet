package com.akendardi.cryptowallet.presentation.coin_info_screen.information

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.states.crypto.CryptoInfoLoadingResult
import com.akendardi.cryptowallet.domain.usecase.crypto.LoadCoinInfoUseCase
import com.akendardi.cryptowallet.presentation.coin_info_screen.CoinInfoState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@HiltViewModel(assistedFactory = CoinInfoInformationViewModel.Factory::class)
class CoinInfoInformationViewModel @AssistedInject constructor(
    @Assisted private val symbol: String,
    private val loadCoinInfoUseCase: LoadCoinInfoUseCase
) : ViewModel() {

    private val TAG: String = "CoinInfoInformationViewModel"
    private val _state = MutableStateFlow(CoinInfoInformationScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            subscribes()
            loadCoinInfoUseCase(symbol)

        }
    }

    fun changeGraphType() {
        _state.update {
            it.copy(
                graphicType = if (it.graphicType == GraphicType.HOUR) GraphicType.DAY else GraphicType.HOUR
            )
        }
    }


    private fun subscribes() {
        viewModelScope.launch {
            loadCoinInfoUseCase.subscribeCoinInfo().collect { result ->
                when (result) {
                    is CryptoInfoLoadingResult.Error -> {

                    }

                    CryptoInfoLoadingResult.Initial -> {

                    }

                    CryptoInfoLoadingResult.Loading -> {
                        startFirstLoading()
                    }

                    is CryptoInfoLoadingResult.Success -> {
                        updateState(result)
                    }

                }
            }
        }


    }

    private fun startFirstLoading() {
        Log.d("TEST_FLOW", "loading viewmodel ")
        _state.update {
            it.copy(
                isFirstLoading = true
            )
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
                list.add(listOf(hour[0], "", ""))
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


        _state.value = CoinInfoInformationScreenState(
            coinInfoState = CoinInfoState(
                symbol = result.coinInfo.symbol,
                name = result.coinInfo.name
            ),
            hourGraphicState = GraphicPriceState(
                timeStampsOnScreen = clearHours,
                pricesOnScreen = result.coinInfo.pricesHour.map { it.price }.selectSixPrices(),
                prices = result.coinInfo.pricesHour.map { it.price }
            ),
            dayGraphicState = GraphicPriceState(
                timeStampsOnScreen = screenTimesDays,
                pricesOnScreen = result.coinInfo.pricesDay.map { it.price }.selectSixPrices(),
                prices = result.coinInfo.pricesDay.map { it.price }
            )
        )
    }

    private fun List<Double>.selectSixPrices(): List<String> {
        if (this.size <= 6) return formatPrice(this)
        val step = (this.max() - this.min()) / 5
        val list = List(6) { i -> (this.min() + step * i) }

        return formatPrice(list.reversed())
    }

    private fun formatPrice(prices: List<Double>): List<String> {
        val maxValue = prices.maxOrNull() ?: 0.0
        return if (maxValue in 0.0..1.0) {
            prices.map { String.format(locale = Locale.getDefault(), format = "%.5f", it) }
        } else if (maxValue <= 1000) {
            prices.map { String.format(locale = Locale.getDefault(), format = "%.2f", it) }
        } else {
            prices.map {
                String.format(
                    locale = Locale.getDefault(),
                    format = "%.2f",
                    it / 1000
                ) + "k"
            }
        }
    }


    private fun formatUnixTimestampsForDays(timestamps: List<Long>): List<List<String>> {
        val formatter = DateTimeFormatter.ofPattern("dd MMM E")
        val zoneId = ZoneId.systemDefault()

        return timestamps.map { timestamp ->
            val dateTime = Instant.ofEpochSecond(timestamp).atZone(zoneId)
            val formattedDate = dateTime.format(formatter)

            formattedDate.split(" ").map {

                it.replaceFirstChar { char ->
                    if (char.isLowerCase()) char.titlecase(
                        Locale.ROOT
                    ) else char.toString()
                }
            }
        }
    }

    private fun formatUnixTimestampsForHours(timestamps: List<Long>): List<List<String>> {
        val formatter = DateTimeFormatter.ofPattern("HH dd MMM") // Формат: "часы день месяц"
        val zoneId = ZoneId.systemDefault()

        return timestamps.map { timestamp ->
            val dateTime = Instant.ofEpochSecond(timestamp).atZone(zoneId)
            val formattedDateTime = dateTime.format(formatter)

            formattedDateTime.split(" ").map {
                it.replaceFirstChar { char ->
                    if (char.isLowerCase()) char.titlecase(Locale.ROOT) else char.toString()
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(symbol: String): CoinInfoInformationViewModel
    }
}