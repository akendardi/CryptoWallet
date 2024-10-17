package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.LoadUsersInfoUseCase
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.change_info.ChangeUserInfoUseCase
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
    private val loadUsersInfoUseCase: LoadUsersInfoUseCase,
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
            loadUsersInfoUseCase.observeUserInfo().collect { userInfo ->
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
                email = tempEmail
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
        if (!isFirstAttempt) {
            val error = passwordValidator.getPasswordError(passwordValidator(password))
            setPasswordError(error)
        }
    }

    fun saveChange() {
        isFirstAttempt = false
        if (isEmailCorrect() && isPasswordCorrect()) {
            viewModelScope.launch {
                changeUserInfoUseCase.changeEmail(
                    state.value.email,
                    state.value.password
                )
            }
        }
        val passwordError = passwordValidator.getPasswordError(passwordValidator(state.value.password))
        setPasswordError(passwordError)
        val emailError = emailValidator.getEmailError(emailValidator(state.value.email))
        setEmailError(emailError)
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

    private fun setPasswordError(error: String) {
        _state.update {
            it.copy(
                passwordError = error
            )
        }
    }


}