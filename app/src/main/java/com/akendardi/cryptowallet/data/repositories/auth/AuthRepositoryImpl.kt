package com.akendardi.cryptowallet.data.repositories.auth

import android.content.Context
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.states.auth.AuthResult
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    @ApplicationContext private val context: Context
) : AuthRepository {


    private val _authState = MutableStateFlow<AuthResult>(AuthResult.Initial)
    override val authState: StateFlow<AuthResult> = _authState.asStateFlow()


    override suspend fun createAccountWithEmail(
        name: String,
        email: String,
        password: String
    ) {
        try {
            startLoading()
            createAccount(
                name = name,
                email = email,
                password = password
            )
            _authState.emit(AuthResult.SuccessCreatedAccount)
        } catch (e: FirebaseAuthUserCollisionException) {
            val errorMessage = context.getString(R.string.error_user_collision)
            _authState.emit(AuthResult.Error(errorMessage))
        }
    }

    private suspend fun createAccount(
        name: String,
        email: String,
        password: String
    ){
        auth.createUserWithEmailAndPassword(email, password).await()
        auth.currentUser?.updateProfile(
            UserProfileChangeRequest.Builder().setDisplayName(name).build()
        )?.await()
    }


    override suspend fun logInAccount(email: String, password: String) {
        try {
            startLoading()
            auth.signInWithEmailAndPassword(email, password).await()
            _authState.emit(AuthResult.SuccessLogin)
        } catch (e: FirebaseException) {
            emitLoginError(e)
        }
    }

    private suspend fun emitLoginError(e: FirebaseException){
        when (e) {
            is FirebaseAuthInvalidCredentialsException -> {
                val errorMessage = if (e.errorCode == "ERROR_WRONG_PASSWORD") {
                    context.getString(R.string.error_wrong_password)
                } else {
                    context.getString(R.string.error_invalid_credentials)
                }
                emitError(errorMessage)
            }

            is FirebaseAuthInvalidUserException -> {
                emitError(context.getString(R.string.error_user_not_found))
            }

            is FirebaseTooManyRequestsException -> {
                emitError(context.getString(R.string.error_too_many_requests))
            }

            else -> {
                emitError(context.getString(R.string.error_unknown, e.message))
            }
        }
    }



    override suspend fun resetPasswordWithEmail(email: String) {
        try {
            startLoading()
            auth.sendPasswordResetEmail(email).await()
            _authState.emit(AuthResult.SuccessSentLink)

        } catch (e: Exception) {
            val errorMessage = context.getString(R.string.error_unknown, e.message)
            emitError(errorMessage)
        }
    }

    override  fun logOutFromAccount() {
        auth.signOut()
    }

    override suspend fun checkCurrentUserIsLogged(): Boolean {
        try {
            auth.currentUser?.reload()?.await()

            val curr = auth.currentUser

            return curr != null
        } catch (e: Exception) {
            return false
        }
    }

    private suspend fun startLoading(){
        _authState.emit(AuthResult.Loading)
    }

    private suspend fun emitError(error: String) {
        _authState.emit(AuthResult.Error(error))
    }

}