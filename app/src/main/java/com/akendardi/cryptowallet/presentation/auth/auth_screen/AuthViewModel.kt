package com.akendardi.cryptowallet.presentation.auth.auth_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.states.auth.AuthResult
import com.akendardi.cryptowallet.domain.usecase.auth.CreateAccountUseCase
import com.akendardi.cryptowallet.domain.usecase.auth.LogInAccountUseCase
import com.akendardi.cryptowallet.domain.usecase.auth.ResetPasswordUseCase
import com.akendardi.cryptowallet.presentation.auth.auth_screen.auth_usecase.EmailValidationResult
import com.akendardi.cryptowallet.presentation.auth.auth_screen.auth_usecase.EmailValidator
import com.akendardi.cryptowallet.presentation.auth.auth_screen.auth_usecase.PasswordValidationResult
import com.akendardi.cryptowallet.presentation.auth.auth_screen.auth_usecase.PasswordValidator
import com.akendardi.cryptowallet.presentation.auth.auth_screen.auth_usecase.UserNameValidator
import com.akendardi.cryptowallet.presentation.auth.auth_screen.auth_usecase.UsernameValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val NOT_LONG_ENOUGH = "Пароль слишком короткий"
private const val NOT_ENOUGH_DIGITS = "Пароль должен содержать хотя бы 1 цифру"
private const val NOT_ENOUGH_UPPERCASE = "Пароль должен содержать заглавную букву"
private const val INCORRECT_FORMAT = "Некорректный формат Email"
private const val IS_EMPTY = "Никнейм не должен быть пустым"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val logInAccountUseCase: LogInAccountUseCase,
    private val createAccountUseCase: CreateAccountUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val passwordValidator: PasswordValidator,
    private val emailValidator: EmailValidator,
    private val userNameValidator: UserNameValidator
) : ViewModel() {

    private val _state = MutableStateFlow(UiAuthState())
    val state: StateFlow<UiAuthState>
        get() = _state.asStateFlow()

    private var isFirstAttempt = false

    private fun createAccount() {
        isFirstAttempt = true
        validateFields()

        if (hasErrors()) return

        val name = _state.value.textFieldsState.userName
        val email = _state.value.textFieldsState.email
        val password = _state.value.textFieldsState.password

        viewModelScope.launch {
            _state.update { it.copy(authResult = AuthResult.Loading) }
            val result = createAccountUseCase(name, email, password)
            _state.update { it.copy(authResult = result) }
        }
    }

    fun sendAuthRequest() {
        when(_state.value.authType){
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
        validateEmail(_state.value.textFieldsState.email)
        if (_state.value.textFieldsErrorsState.emailError.isNotEmpty()) return
        val email = _state.value.textFieldsState.email
        viewModelScope.launch {
            _state.update {
                it.copy(
                    authResult = AuthResult.Loading
                )
            }
            val result = resetPasswordUseCase(email)
            _state.update {
                it.copy(
                    authResult = result
                )
            }
        }
    }

    private fun loginWithEmailAndPassword() {
        isFirstAttempt = true
        validateFields()

        if (hasErrors()) return

        val email = state.value.textFieldsState.email
        val password = state.value.textFieldsState.password

        viewModelScope.launch {
            _state.update { it.copy(authResult = AuthResult.Loading) }
            val result = logInAccountUseCase(email, password)
            Log.d("PIZDA", result.toString())
            _state.update { it.copy(authResult = result) }
        }
    }

    fun onPassword(password: String) {
        val clearPassword = password.trim()
        _state.update {
            it.copy(
                textFieldsState = it.textFieldsState.copy(password = clearPassword)
            )
        }
        if (isFirstAttempt) validatePassword(clearPassword)
    }

    fun actionTextField(type: FieldType){
        when(type){
            FieldType.USERNAME -> {
                _state.update {
                    it.copy(
                        textFieldsState = it.textFieldsState.copy(
                            userName = ""
                        )
                    )
                }
            }
            FieldType.EMAIL -> {
                _state.update {
                    it.copy(
                        textFieldsState = it.textFieldsState.copy(
                            email = ""
                        )
                    )
                }
            }
            FieldType.PASSWORD -> {
                _state.update {
                    it.copy(
                        textFieldsState = it.textFieldsState.copy(
                            isPasswordVisible = !it.textFieldsState.isPasswordVisible
                        )
                    )
                }
            }
        }
    }

    fun onEmail(email: String) {
        val clearEmail = email.trim()
        _state.update {
            it.copy(
                textFieldsState = it.textFieldsState.copy(email = clearEmail)
            )
        }
        if (isFirstAttempt) validateEmail(clearEmail)
    }

    fun onUsername(username: String) {
        val clearName = username.trim()
        _state.update {
            it.copy(
                textFieldsState = it.textFieldsState.copy(userName = clearName)
            )
        }
        if (isFirstAttempt) validateUsername(clearName)
    }

    private fun validateFields() {
        validatePassword(state.value.textFieldsState.password)
        validateEmail(state.value.textFieldsState.email)
        validateUsername(state.value.textFieldsState.userName)
    }

    private fun validatePassword(password: String) {
        val result = when (passwordValidator(password)) {
            PasswordValidationResult.NOT_LONG_ENOUGH -> NOT_LONG_ENOUGH
            PasswordValidationResult.NOT_ENOUGH_DIGITS -> NOT_ENOUGH_DIGITS
            PasswordValidationResult.NOT_ENOUGH_UPPERCASE -> NOT_ENOUGH_UPPERCASE
            PasswordValidationResult.CORRECT -> ""
        }
        _state.update {
            it.copy(
                textFieldsErrorsState = it.textFieldsErrorsState.copy(passwordError = result)
            )
        }
    }

    private fun validateEmail(email: String) {
        val result = when (emailValidator(email)) {
            EmailValidationResult.INCORRECT_FORMAT -> INCORRECT_FORMAT
            EmailValidationResult.CORRECT -> ""
        }
        _state.update {
            it.copy(
                textFieldsErrorsState = it.textFieldsErrorsState.copy(emailError = result)
            )
        }
    }

    private fun validateUsername(username: String) {
        val result = when (userNameValidator(username)) {
            UsernameValidationResult.IS_EMPTY -> IS_EMPTY
            UsernameValidationResult.CORRECT -> ""
        }
        _state.update {
            it.copy(
                textFieldsErrorsState = it.textFieldsErrorsState.copy(userNameError = result)
            )
        }
    }

    fun changeAuthType(authType: AuthType) {
        val textFields = when (authType) {
            AuthType.SIGN_IN -> TextFieldsState(email = _state.value.textFieldsState.email)
            AuthType.SIGN_UP -> TextFieldsState(
                email = _state.value.textFieldsState.email,
                password = _state.value.textFieldsState.password
            )

            AuthType.RESET_PASSWORD -> TextFieldsState(email = _state.value.textFieldsState.email)
        }
        _state.update {
            it.copy(
                authType = authType,
                textFieldsErrorsState = TextFieldsErrorsState(),
                textFieldsState = textFields
            )
        }
        isFirstAttempt = false
    }

    private fun hasErrors(): Boolean {
        return if (_state.value.authType == AuthType.SIGN_IN) {
            !(_state.value.textFieldsErrorsState.emailError == "" &&
                    _state.value.textFieldsErrorsState.passwordError == "")
        } else {
            !(_state.value.textFieldsErrorsState.emailError == "" &&
                    _state.value.textFieldsErrorsState.passwordError == "" &&
                    _state.value.textFieldsErrorsState.userNameError == "")
        }
    }
}
