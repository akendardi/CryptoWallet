package com.akendardi.cryptowallet.presentation.auth.auth_screen

import androidx.compose.runtime.Stable
import com.akendardi.cryptowallet.domain.states.auth.AuthResult
@Stable
data class UiAuthState(
    val authType: AuthType = AuthType.SIGN_IN,
    val textFieldsState: TextFieldsState = TextFieldsState(),
    val textFieldsErrorsState: TextFieldsErrorsState = TextFieldsErrorsState(),
    val authResult: AuthResult = AuthResult.Initial,
    val authButtonText: String
)

enum class AuthType {
    SIGN_IN, SIGN_UP, RESET_PASSWORD
}

data class TextFieldsState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false
)

data class TextFieldsErrorsState(
    val userNameError: String = "",
    val emailError: String = "",
    val passwordError: String = ""
)

enum class FieldType {
    USERNAME,
    EMAIL,
    PASSWORD
}