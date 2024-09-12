package com.akendardi.cryptowallet.data.repositories.auth

import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.states.AuthState
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
            auth.createUserWithEmailAndPassword(email, password).await()
            authState.value = AuthState.SuccessCreateAccount
        } catch (e: Exception) {
            authState.value = AuthState.Error(e)
        }
    }

    override suspend fun logInAccount(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password)
            authState.value = AuthState.SuccessLogin
        } catch (e: Exception) {
            authState.value = AuthState.Error(e)
        }
    }

    override suspend fun resetPasswordWithEmail(email: String) {
        try {
            auth.sendPasswordResetEmail(email)
            authState.value = AuthState.SuccessSentLinkToResetPassword
        } catch (e: Exception) {
            authState.value = AuthState.Error(e)
        }
    }

    override suspend fun logOutFromAccount() {
        try {
            auth.signOut()
            authState.value = AuthState.SuccessLogout
        } catch (e: Exception) {
            authState.value = AuthState.Error(e)
        }
    }
}