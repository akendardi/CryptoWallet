package com.akendardi.cryptowallet.domain.states

sealed class AuthState {
    data object Initial : AuthState()

    data object SuccessLogin : AuthState()


    data object SuccessCreateAccount : AuthState()

    data object SuccessSentLinkToResetPassword : AuthState()

    data object SuccessLogout : AuthState()


    data class Error(val e: Exception) : AuthState()
}