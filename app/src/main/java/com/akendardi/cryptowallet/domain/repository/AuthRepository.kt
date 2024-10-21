package com.akendardi.cryptowallet.domain.repository

import com.akendardi.cryptowallet.domain.states.auth.AuthResult

interface AuthRepository {

    suspend fun createAccount(
        name: String,
        email: String,
        password: String

    ): AuthResult

    suspend fun logInAccount(
        email: String,
        password: String
    ): AuthResult

    suspend fun logOutFromAccount()

    suspend fun resetPasswordWithEmail(
        email: String
    ): AuthResult

    suspend fun checkCurrentUserIsLogged(): Boolean


    fun checkInternetConnection(): Boolean
}