package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.ChangeUserInfoUseCase
import com.akendardi.cryptowallet.domain.usecase.validators.PasswordValidationResult
import com.akendardi.cryptowallet.domain.usecase.validators.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileAlertPasswordViewModel @Inject constructor(
    private val changeUserInfoUseCase: ChangeUserInfoUseCase,
    private val passwordValidator: PasswordValidator
) : ViewModel() {
    private var isFirstAttempt = true

    private val _state = MutableStateFlow(ProfileAlertPasswordState())
    val state = _state.asStateFlow()

    fun onNewPasswordChanged(value: String) {
        val clearPassword = value.trim()
        setNewPassword(clearPassword)
        if (!isFirstAttempt) {
            val error = passwordValidator.getPasswordError(passwordValidator(clearPassword))
            setNewPasswordError(error)
        }
    }

    fun onOldPasswordChanged(value: String) {
        val clearPassword = value.trim()
        setOldPassword(clearPassword)
    }


    private fun setNewPassword(value: String) {
        _state.update {
            it.copy(
                newPassword = value
            )
        }
    }

    private fun setOldPassword(value: String) {
        _state.update {
            it.copy(
                oldPassword = value
            )
        }
    }

    fun saveChange(): Boolean {
        if (isPasswordCorrect()) {
            viewModelScope.launch {
                changeUserInfoUseCase.changePassword(
                    oldPassword = state.value.oldPassword,
                    newPassword = state.value.newPassword
                )
            }
            return true
        } else {
            val passwordError =
                passwordValidator.getPasswordError(passwordValidator(state.value.newPassword))
            setNewPasswordError(passwordError)
            return false
        }

    }

    private fun isPasswordCorrect(): Boolean {
        return passwordValidator(state.value.newPassword) == PasswordValidationResult.CORRECT
    }

    private fun setNewPasswordError(error: String) {
        _state.update {
            it.copy(
                newPasswordError = error
            )
        }
    }

}