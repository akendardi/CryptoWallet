package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.states.auth.AuthResult
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {

    val authState: StateFlow<AuthResult>

    suspend fun createAccount(
        name: String,
        email: String,
        password: String

    )

    suspend fun logInAccount(
        email: String,
        password: String
    )

    suspend fun logOutFromAccount()

    suspend fun resetPasswordWithEmail(
        email: String
    )

    suspend fun checkCurrentUserIsLogged(): Boolean

}