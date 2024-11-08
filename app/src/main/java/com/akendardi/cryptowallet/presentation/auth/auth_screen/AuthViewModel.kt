package com.akendardi.cryptowallet.presentation.auth.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.usecase.auth.CreateAccountUseCase
import com.akendardi.cryptowallet.domain.usecase.auth.LogInAccountUseCase
import com.akendardi.cryptowallet.domain.usecase.auth.ResetPasswordUseCase
import com.akendardi.cryptowallet.domain.usecase.validators.EmailValidationResult
import com.akendardi.cryptowallet.domain.usecase.validators.EmailValidator
import com.akendardi.cryptowallet.domain.usecase.validators.PasswordValidationResult
import com.akendardi.cryptowallet.domain.usecase.validators.PasswordValidator
import com.akendardi.cryptowallet.domain.usecase.validators.UserNameValidator
import com.akendardi.cryptowallet.domain.usecase.validators.UsernameValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val logInAccountUseCase: LogInAccountUseCase,
    private val createAccountUseCase: CreateAccountUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val passwordValidator: PasswordValidator,
    private val emailValidator: EmailValidator,
    private val userNameValidator: UserNameValidator,
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiAuthState(authButtonText = "Войти"))
    val state: StateFlow<UiAuthState>
        get() = _state.asStateFlow()

    private fun observeAuthResult() {
        viewModelScope.launch {
            repository.authState.collect { result ->
                _state.update {
                    it.copy(
                        authResult = result
                    )
                }
            }
        }
    }

    init {
        observeAuthResult()
    }


    private fun getEmail(): String {
        return _state.value.textFieldsState.email
    }

    private fun getPassword(): String {
        return _state.value.textFieldsState.password
    }

    private fun getUserName(): String {
        return _state.value.textFieldsState.userName
    }

    private fun getAuthType(): AuthType {
        return _state.value.authType
    }

    private var isFirstAttempt = false

    private fun createAccount() {
        isFirstAttempt = true

        if (isHasErrorsForRegister()) return

        val name = getUserName()
        val email = getEmail()
        val password = getPassword()

        viewModelScope.launch {
            createAccountUseCase(name, email, password)
        }
    }

    fun sendAuthRequest() {
        when (getAuthType()) {
            AuthType.SIGN_IN -> {
                loginWithEmailAndPassword()
            }

            AuthType.SIGN_UP -> {
                createAccount()
            }

            AuthType.RESET_PASSWORD -> {
                resetPassword()
            }
        }

    }


    private fun resetPassword() {
        isFirstAttempt = true
        if (isHasErrorsForResetPassword()) return
        val email = getEmail()
        viewModelScope.launch {
            resetPasswordUseCase(email)
        }
    }

    private fun loginWithEmailAndPassword() {
        isFirstAttempt = true

        if (isHasErrorsForLogin()) return

        val email = getEmail()
        val password = getPassword()

        viewModelScope.launch {
            logInAccountUseCase(email, password)
        }
    }


    fun actionTextField(type: FieldType) {
        when (type) {
            FieldType.USERNAME -> userNameIconAction()
            FieldType.EMAIL -> emailIconAction()
            FieldType.PASSWORD -> passwordIconAction()
        }
    }

    private fun userNameIconAction() {
        clearUserNameTextField()
    }

    private fun emailIconAction() {
        clearEmailTextField()
    }

    private fun passwordIconAction() {
        _state.update {
            it.copy(
                textFieldsState = it.textFieldsState.copy(
                    isPasswordVisible = !it.textFieldsState.isPasswordVisible
                )
            )
        }
    }

    fun onPassword(password: String) {
        val clearPassword = password.trim()
        setPassword(clearPassword)
        if (isFirstAttempt) isPasswordTextFieldHasError()
    }

    private fun setPassword(password: String) {
        _state.update {
            it.copy(
                textFieldsState = it.textFieldsState.copy(password = password)
            )
        }
    }

    fun onEmail(email: String) {
        val clearEmail = email.trim()
        setEmail(clearEmail)
        if (isFirstAttempt) isEmailTextFieldHasError()
    }

    private fun setEmail(email: String) {
        _state.update {
            it.copy(
                textFieldsState = it.textFieldsState.copy(email = email)
            )
        }
    }

    fun onUsername(username: String) {
        val clearName = username.trim()
        setUserName(clearName)
        if (isFirstAttempt) isUsernameTextFieldHasError()
    }

    private fun setUserName(username: String) {
        _state.update {
            it.copy(
                textFieldsState = it.textFieldsState.copy(userName = username)
            )
        }
    }


    fun changeAuthType(authType: AuthType) {
        clearAllErrors()
        when (authType) {
            AuthType.SIGN_IN -> {
                clearUserNameTextField()
                setSignInAuthType()
            }

            AuthType.SIGN_UP -> {
                setRegisterAuthType()
            }

            AuthType.RESET_PASSWORD -> {
                clearPasswordTextField()
                clearUserNameTextField()
                setResetPasswordAuthType()
            }
        }
        isFirstAttempt = false
    }

    private fun setRegisterAuthType() {
        _state.update {
            it.copy(
                authType = AuthType.SIGN_UP
            )
        }
    }

    private fun setResetPasswordAuthType() {
        _state.update {
            it.copy(
                authType = AuthType.RESET_PASSWORD
            )
        }
    }

    private fun setSignInAuthType() {
        _state.update {
            it.copy(
                authType = AuthType.SIGN_IN
            )
        }
    }

    private fun clearPasswordTextField() {
        _state.update {
            it.copy(
                textFieldsState = it.textFieldsState.copy(
                    password = ""
                )
            )
        }
    }

    private fun clearEmailTextField() {
        _state.update {
            it.copy(
                textFieldsState = it.textFieldsState.copy(
                    email = ""
                )
            )
        }
    }

    private fun clearUserNameTextField() {
        _state.update {
            it.copy(
                textFieldsState = it.textFieldsState.copy(
                    userName = ""
                )
            )
        }
    }

    private fun isHasErrorsForResetPassword(): Boolean {
        return !isEmailTextFieldHasError()
    }

    private fun isHasErrorsForRegister(): Boolean {
        val isEmailError = isEmailTextFieldHasError()
        val isPasswordError = isPasswordTextFieldHasError()
        val isUserError = isUsernameTextFieldHasError()
        return !(isEmailError
                && isPasswordError
                && isUserError)
    }

    private fun isHasErrorsForLogin(): Boolean {
        val isEmailError = isEmailTextFieldHasError()
        val isPasswordError = isPasswordTextFieldHasError()
        return !(isEmailError
                && isPasswordError)
    }

    private fun isPasswordTextFieldHasError(): Boolean {
        val passwordValidationResult = passwordValidator(
            getPassword()
        )
        val passwordError = passwordValidator.getPasswordError(passwordValidationResult)
        setPasswordError(passwordError)
        return passwordValidationResult == PasswordValidationResult.CORRECT
    }

    private fun setPasswordError(error: String) {
        _state.update {
            it.copy(
                textFieldsErrorsState = it.textFieldsErrorsState.copy(
                    passwordError = error
                )
            )
        }
    }

    private fun isEmailTextFieldHasError(): Boolean {
        val emailValidationResult = emailValidator(
            getEmail()
        )
        val emailError = emailValidator.getEmailError(emailValidationResult)
        setEmailError(emailError)
        return emailValidationResult == EmailValidationResult.CORRECT
    }


    private fun setEmailError(error: String) {
        _state.update {
            it.copy(
                textFieldsErrorsState = it.textFieldsErrorsState.copy(
                    emailError = error
                )
            )
        }
    }

    private fun isUsernameTextFieldHasError(): Boolean {
        val userNameValidationResult = userNameValidator(
            getUserName()
        )
        val userNameError = userNameValidator.getUsernameError(userNameValidationResult)
        setUsernameError(userNameError)
        return userNameValidationResult == UsernameValidationResult.CORRECT
    }


    private fun setUsernameError(error: String) {
        _state.update {
            it.copy(
                textFieldsErrorsState = it.textFieldsErrorsState.copy(
                    userNameError = error
                )
            )
        }
    }

    private fun clearAllErrors() {
        _state.update {
            it.copy(
                textFieldsErrorsState = TextFieldsErrorsState()
            )
        }
    }
}
