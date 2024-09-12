package com.akendardi.cryptowallet.data.repositories.auth

import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.states.AuthState
import com.akendardi.cryptowallet.domain.states.AuthState.Success
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override val authState = MutableStateFlow(AuthState.Initial as AuthState)
    override suspend fun createAccount(name: String, password: String, email: String) {
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            authState.value = Success
        } catch (e: Exception) {
            authState.value = AuthState.Error(e)
        }
    }

    override suspend fun logInAccount(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun logOutFromAccount() {
        TODO("Not yet implemented")
    }
}