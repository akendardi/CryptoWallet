package com.akendardi.cryptowallet.presentation.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.UpdateUserProfileImageUseCase
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.UsersInfoUseCase
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
    private val usersInfoUseCase: UsersInfoUseCase,
    private val updateUserProfileImageUseCase: UpdateUserProfileImageUseCase
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


    fun closeAnswerScreen() {
        resetRequestAnswer()
    }

    fun resetRequestAnswer() {
        viewModelScope.launch {
            usersInfoUseCase.resetRequestAnswer()
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
                usersInfoUseCase.observeUserInfo().collect { userInfo ->
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
                usersInfoUseCase.observeRequestAnswers().collect { answer ->
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

    fun uploadProfilePhoto(uri: Uri) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updateUserProfileImageUseCase(uri)
            }
        }
    }

}