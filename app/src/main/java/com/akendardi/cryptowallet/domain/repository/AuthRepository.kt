package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.states.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val authState: StateFlow<AuthState>

    suspend fun createAccount(
        name: String,
        password: String,
        email: String
    )

    suspend fun logInAccount(
        email: String,
        password: String
    )

    suspend fun logOutFromAccount()

    suspend fun resetPasswordWithEmail(
        email: String
    )

     fun checkCurrentUserIsLogged(): Boolean

    fun checkInternetConnection(): Boolean
}