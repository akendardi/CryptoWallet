package com.akendardi.cryptowallet.domain.states

sealed class AuthState {
    data object Initial: AuthState()

    data object Success: AuthState()

    data class Error(val e: Exception): AuthState()
}