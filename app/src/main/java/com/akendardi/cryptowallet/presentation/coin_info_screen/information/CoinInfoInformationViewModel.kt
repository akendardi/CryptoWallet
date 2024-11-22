package com.akendardi.cryptowallet.presentation.coin_info_screen.information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.states.crypto.CryptoInfoLoadingResult
import com.akendardi.cryptowallet.domain.usecase.crypto.LoadCoinInfoUseCase
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.PriceConverter.Companion.formatPrice
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.PriceConverter.Companion.selectSixPrices
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.TimeConverter.Companion.clearHoursDates
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.TimeConverter.Companion.formatUnixTimeToHoursAndMinutes
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.TimeConverter.Companion.formatUnixTimestampsForDays
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.TimeConverter.Companion.formatUnixTimestampsForHours
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.TimeConverter.Companion.selectSixDates
import com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils.TimeConverter.Companion.selectSixHours
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = CoinInfoInformationViewModel.Factory::class)
class CoinInfoInformationViewModel @AssistedInject constructor(
    @Assisted private val symbol: String,
    private val loadCoinInfoUseCase: LoadCoinInfoUseCase
) : ViewModel() {

    private val TAG: String = "CoinInfoInformationViewModel"
    private val _state = MutableStateFlow(CoinInfoInformationScreenState())
    val state = _state.asStateFlow()

    init {
        subscribes()
        loadData()

    }

    fun changeGraphType() {
        _state.update {
            it.copy(
                graphicType = if (it.graphicType == GraphicType.HOUR) GraphicType.DAY else GraphicType.HOUR
            )
        }
    }

    fun loadData() = viewModelScope.launch {
        loadCoinInfoUseCase(symbol)
    }


    private fun subscribes() {
        viewModelScope.launch {
            loadCoinInfoUseCase.subscribeCoinInfo().collect { result ->
                when (result) {
                    is CryptoInfoLoadingResult.Error -> {

                    }

                    CryptoInfoLoadingResult.Initial -> {}

                    CryptoInfoLoadingResult.Loading -> {
                        startLoading()
                    }

                    is CryptoInfoLoadingResult.Success -> {
                        updateState(result)
                    }
                }
            }
        }


    }

    private fun startLoading() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
    }


    private fun updateState(result: CryptoInfoLoadingResult.Success) {
        val clearDays = getDays(result)

        val clearHours = getHours(result)

        val daysPrices = selectSixPrices(result.coinInfo.pricesDay.map { it.price })
        val hourPrices = selectSixPrices(result.coinInfo.pricesHour.map { it.price })

        val price = formatPrice(result.coinInfo.currentPrice)
        val lastTimeUpdate = formatUnixTimeToHoursAndMinutes(result.coinInfo.lastUpdate)


        _state.value = CoinInfoInformationScreenState(
            isLoading = false,
            coinInfoState = CoinInfoDetailState(
                symbol = result.coinInfo.symbol,
                name = result.coinInfo.name,
                price = price,
                lastDateUpdate = lastTimeUpdate
            ),
            hourGraphicState = GraphicPriceState(
                timeStampsOnScreen = clearHours,
                pricesOnScreen = hourPrices,
                prices = result.coinInfo.pricesHour.map { it.price }
            ),
            dayGraphicState = GraphicPriceState(
                timeStampsOnScreen = clearDays,
                pricesOnScreen = daysPrices,
                prices = result.coinInfo.pricesDay.map { it.price }
            )
        )
    }

    private fun getDays(result: CryptoInfoLoadingResult.Success): List<List<String>> {
        val timesDays =
            formatUnixTimestampsForDays(result.coinInfo.pricesDay.map { it.time })
        return selectSixDates(timesDays)
    }

    private fun getHours(result: CryptoInfoLoadingResult.Success): List<List<String>> {
        val timesHours =
            formatUnixTimestampsForHours(result.coinInfo.pricesHour.map { it.time })
        return clearHoursDates(selectSixHours(timesHours))
    }

    @AssistedFactory
    interface Factory {
        fun create(symbol: String): CoinInfoInformationViewModel
    }


}