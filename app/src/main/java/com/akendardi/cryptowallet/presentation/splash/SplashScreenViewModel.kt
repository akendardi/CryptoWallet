package com.akendardi.cryptowallet.presentation.splash

import androidx.lifecycle.ViewModel
import com.akendardi.cryptowallet.domain.usecase.auth.CheckCurrentUserIsLoggedUseCase
import com.akendardi.cryptowallet.domain.usecase.auth.CheckInternetConnectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val checkCurrentUserIsLoggedUseCase: CheckCurrentUserIsLoggedUseCase,
    private val checkInternetConnectionUseCase: CheckInternetConnectionUseCase
) : ViewModel() {


    private val _state = MutableStateFlow<SplashState>(SplashState.Initial)
    val state: StateFlow<SplashState> get() = _state.asStateFlow()

    fun checkLogState() {
        if (checkInternetConnectionUseCase()) {
            if (checkCurrentUserIsLoggedUseCase()) {
                _state.value = SplashState.Success(NextScreen.Main)
            } else {
                _state.value = SplashState.Success(NextScreen.Login)
            }
        } else {
            _state.value = SplashState.NetworkError
        }
    }
}



















