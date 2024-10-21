package com.akendardi.cryptowallet.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.usecase.auth.CheckCurrentUserIsLoggedUseCase
import com.akendardi.cryptowallet.domain.usecase.auth.CheckInternetConnectionUseCase
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.UsersInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val checkCurrentUserIsLoggedUseCase: CheckCurrentUserIsLoggedUseCase,
    private val checkInternetConnectionUseCase: CheckInternetConnectionUseCase,
    private val usersInfoUseCase: UsersInfoUseCase
) : ViewModel() {


    private val _state = MutableStateFlow<SplashState>(SplashState.Initial)
    val state: StateFlow<SplashState> get() = _state.asStateFlow()


    fun checkLogState() {
        if (checkInternetConnectionUseCase()) {
            viewModelScope.launch {
                if (checkCurrentUserIsLoggedUseCase()) {
                    withContext(Dispatchers.IO) {
                        usersInfoUseCase()
                    }
                    usersInfoUseCase.observeUserInfo().collect {
                        _state.value = SplashState.Success(NextScreen.Main)

                    }
                } else {
                    _state.value = SplashState.Success(NextScreen.Login)
                }
            }
        } else {
            _state.value = SplashState.NetworkError
        }
    }
}



















