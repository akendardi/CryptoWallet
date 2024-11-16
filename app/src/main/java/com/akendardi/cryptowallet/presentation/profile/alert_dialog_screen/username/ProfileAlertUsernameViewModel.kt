package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.username

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.UsersInfoUseCase
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.ChangeUserInfoUseCase
import com.akendardi.cryptowallet.domain.usecase.validators.UserNameValidator
import com.akendardi.cryptowallet.domain.usecase.validators.UsernameValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileAlertUsernameViewModel @Inject constructor(
    private val usersInfoUseCase: UsersInfoUseCase,
    private val changeUserInfoUseCase: ChangeUserInfoUseCase,
    private val userNameValidator: UserNameValidator
) : ViewModel() {
    private var isFirstAttempt = true

    private val _state = MutableStateFlow(ProfileAlertUsernameState())
    val state = _state.asStateFlow()

    private var tempName = ""

    init {
        observeUserInfo()
    }

    private fun observeUserInfo(){
        viewModelScope.launch {
            usersInfoUseCase.observeUserInfo().collect { userInfo ->
                _state.update {
                    it.copy(
                        name = userInfo.userName
                    )
                }
                tempName = userInfo.userName
            }
        }
    }

    fun resetInfo(){
        isFirstAttempt = true
        _state.update {
            it.copy(
                name = tempName,
                error = ""
            )
        }
    }


    fun onUserNameChanged(value: String) {
        val clearUserName = value.trim()
        setUserName(clearUserName)
        if (!isFirstAttempt) {
            val error = userNameValidator.getUsernameError(userNameValidator(clearUserName))
            setUserNameError(error)
        }
    }

    private fun isCorrectUserName(): Boolean{
        return userNameValidator(_state.value.name) == UsernameValidationResult.CORRECT
    }

    fun saveChange(): Boolean {
        isFirstAttempt = false
        if (isCorrectUserName()){
            viewModelScope.launch {
                changeUserInfoUseCase.changeName(state.value.name)
            }
            resetInfo()
            return true
        } else {
            val error = userNameValidator.getUsernameError(userNameValidator(state.value.name))
            setUserNameError(error)
            return false
        }
    }

    private fun setUserName(value: String) {
        _state.update {
            it.copy(
                name = value
            )
        }
    }

    private fun setUserNameError(error: String) {
        _state.update {
            it.copy(
                error = error
            )
        }
    }


}