package com.akendardi.cryptowallet.data.repositories.auth

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.states.AuthState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    @ApplicationContext private val context: Context
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

    override fun checkCurrentUserIsLogged(): Boolean {
        return (auth.currentUser != null)
    }

    override fun checkInternetConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}