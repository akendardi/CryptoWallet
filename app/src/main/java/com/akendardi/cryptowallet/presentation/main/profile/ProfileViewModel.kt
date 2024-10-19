package com.akendardi.cryptowallet.presentation.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.LoadUsersInfoUseCase
import com.akendardi.cryptowallet.settings.SettingsManager
import com.akendardi.cryptowallet.settings.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val settingsManager: SettingsManager,
    private val loadUsersInfoUseCase: LoadUsersInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()


    init {
        subscribeUserInfoFlow()
        subscribeRequestAnswer()
        subscribeNotifications()
        subscribeTheme()
    }


    fun openSettingAlertScreen(screen: ProfileScreen) {
        _state.update {
            it.copy(
                currentScreen = screen
            )
        }
    }

    fun closeAlertScreen() {
        _state.update {
            it.copy(
                currentScreen = ProfileScreen.Profile
            )
        }
    }


    fun closeAnswerScreen(){
        viewModelScope.launch {
            loadUsersInfoUseCase.resetRequestAnswer()
        }

    }


    fun changeTheme(themeMode: ThemeMode) {
        viewModelScope.launch {
            settingsManager.setThemeMode(themeMode)
        }
    }

    fun changeNotificationsMode() {
        viewModelScope.launch {
            settingsManager.changeNotificationsEnabled()
        }
    }

    private fun subscribeUserInfoFlow() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loadUsersInfoUseCase.observeUserInfo().collect { userInfo ->
                    _state.update {
                        it.copy(
                            userInfo = userInfo
                        )
                    }
                }

            }
        }
    }

    private fun subscribeRequestAnswer() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loadUsersInfoUseCase.observeRequestAnswers().collect { answer ->
                    Log.d("TEST_ANSWER", answer.toString())
                    _state.update {
                        it.copy(
                            requestAnswer = answer
                        )
                    }
                }

            }
        }
    }

    private fun subscribeTheme() {
        viewModelScope.launch {
            settingsManager.themeModeFlow.collect { themeMode ->
                _state.update {
                    it.copy(
                        themeMode = themeMode,
                    )
                }
            }
        }
    }

    private fun subscribeNotifications() {
        viewModelScope.launch {
            settingsManager.notificationsEnabledFlow.collect { isEnabled ->
                _state.update {
                    it.copy(
                        isNotificationsEnables = isEnabled,
                    )
                }
            }
        }
    }

}