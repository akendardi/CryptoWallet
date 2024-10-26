package com.akendardi.cryptowallet.domain.states.auth

sealed class AuthResult {
    data object Initial : AuthResult()

    data object SuccessCreatedAccount: AuthResult()
    data object SuccessLogin: AuthResult()

    data object SuccessSentLink : AuthResult()

    data object Loading : AuthResult()


    data class Error(val e: String) : AuthResult()
}