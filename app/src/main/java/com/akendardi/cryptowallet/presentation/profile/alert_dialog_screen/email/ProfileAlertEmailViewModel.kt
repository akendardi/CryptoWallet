package com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.UsersInfoUseCase
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.ChangeUserInfoUseCase
import com.akendardi.cryptowallet.domain.usecase.validators.EmailValidationResult
import com.akendardi.cryptowallet.domain.usecase.validators.EmailValidator
import com.akendardi.cryptowallet.domain.usecase.validators.PasswordValidationResult
import com.akendardi.cryptowallet.domain.usecase.validators.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileAlertEmailViewModel @Inject constructor(
    private val usersInfoUseCase: UsersInfoUseCase,
    private val changeUserInfoUseCase: ChangeUserInfoUseCase,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) : ViewModel() {
    private var isFirstAttempt = true

    private val _state = MutableStateFlow(ProfileAlertEmailState())
    val state = _state.asStateFlow()

    private var tempEmail = ""

    init {
        observeUserInfo()
    }

    private fun observeUserInfo() {
        viewModelScope.launch {
            usersInfoUseCase.observeUserInfo().collect { userInfo ->
                _state.update {
                    it.copy(
                        email = userInfo.email
                    )
                }
                tempEmail = userInfo.email
            }
        }
    }

    fun resetInfo() {
        isFirstAttempt = true
        _state.update {
            it.copy(
                email = tempEmail,
                password = "",
                passwordError = ""
            )
        }
    }


    fun onEmailChanged(value: String) {
        val clearEmail = value.trim()
        setEmail(clearEmail)
        if (!isFirstAttempt) {
            val error = emailValidator.getEmailError(emailValidator(clearEmail))
            setEmailError(error)
        }
    }

    fun onPasswordChanged(value: String) {
        val password = value.trim()
        setPassword(password)
    }

    fun saveChange(): Boolean {
        isFirstAttempt = false
        if (isEmailCorrect() && isPasswordCorrect()) {
            viewModelScope.launch {
                changeUserInfoUseCase.changeEmail(
                    state.value.email,
                    state.value.password
                )
            }
            resetInfo()
            return true
        } else {
            val emailError = emailValidator.getEmailError(emailValidator(state.value.email))
            setEmailError(emailError)
            return false
        }
    }

    private fun isPasswordCorrect(): Boolean {
        return passwordValidator(state.value.password) == PasswordValidationResult.CORRECT
    }

    private fun isEmailCorrect(): Boolean {
        return emailValidator(state.value.email) == EmailValidationResult.CORRECT
    }

    private fun setEmail(value: String) {
        _state.update {
            it.copy(
                email = value
            )
        }
    }

    private fun setPassword(value: String) {
        _state.update {
            it.copy(
                password = value
            )
        }
    }

    private fun setEmailError(error: String) {
        _state.update {
            it.copy(
                emailError = error
            )
        }
    }



}