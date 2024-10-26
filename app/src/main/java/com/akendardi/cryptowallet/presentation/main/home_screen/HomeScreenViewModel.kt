package com.akendardi.cryptowallet.presentation.main.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.usecase.auth.LogOutFromAccountUseCase
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.UpdateUserProfileImageUseCase
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.UsersInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val usersInfoUseCase: UsersInfoUseCase,
    private val logOutFromAccountUseCase: LogOutFromAccountUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenUIState())
    val state: StateFlow<HomeScreenUIState> = _state

    init {
        subscribeUserInfoFlow()
        refreshUserInfo()
    }

    private fun subscribeUserInfoFlow() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                usersInfoUseCase.observeUserInfo().collect { userInfo ->
                    _state.update {
                        it.copy(
                            userInfoState = userInfo ?: throw RuntimeException()
                        )
                    }
                }

            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                logOutFromAccountUseCase()
            }
        }
    }

    private fun refreshUserInfo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                usersInfoUseCase()
            }
        }

    }
}