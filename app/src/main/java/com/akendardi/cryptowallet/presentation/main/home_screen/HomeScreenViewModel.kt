package com.akendardi.cryptowallet.presentation.main.home_screen

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.UsersInfoUseCase
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.UpdateUserProfileImageUseCase
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
    private val updateUserProfileImageUseCase: UpdateUserProfileImageUseCase,
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
                    Log.d("ViewModelTest", "Emitted $userInfo")
                    Log.d("ViewModelTest", "State ${state.value}")
                }

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

    fun updateUserProfileImage(uri: Uri) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updateUserProfileImageUseCase(uri)
            }
        }
    }
}