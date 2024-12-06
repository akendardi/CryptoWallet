package com.akendardi.cryptowallet.data.repositories.auth

import android.content.Context
import android.util.Log
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.domain.entity.user_info.balance.UsersBalance
import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.states.auth.AuthResult
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val remoteDatabase: FirebaseDatabase,
    @ApplicationContext private val context: Context
) : AuthRepository {


    private val _authState = MutableStateFlow<AuthResult>(AuthResult.Initial)
    override val authState: StateFlow<AuthResult> = _authState.asStateFlow()

    init {
        Log.d("TEST_DAGGER", this.toString())
    }


    override suspend fun createAccountWithEmail(
        name: String,
        email: String,
        password: String
    ) {
        try {
            startLoading()
            createAccountAtFirebase(
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

    private suspend fun createAccountAtFirebase(
        name: String,
        email: String,
        password: String
    ) {
        Log.d("AUTH_TEST", "createUserBalanceInFirebaseDb:  create acc ")
        auth.createUserWithEmailAndPassword(email, password).await()
        auth.currentUser?.updateProfile(
            UserProfileChangeRequest.Builder().setDisplayName(name).build()
        )?.await()
        Log.d("AUTH_TEST", "createUserBalanceInFirebaseDb:  ok acc ")
        createUserBalanceInFirebaseDb(auth.currentUser?.uid ?: throw RuntimeException())
    }

    private suspend fun createUserBalanceInFirebaseDb(
        userId: String
    ) {
        val databaseReference = remoteDatabase.reference
        Log.d("AUTH_TEST", "Database reference initialized: $databaseReference")

        val initialBalance = UsersBalance(
            freeBalance = 0.0,
            purchasedCoins = listOf()
        )
        Log.d("AUTH_TEST", "createUserBalanceInFirebaseDb:  create db $userId ")

        databaseReference
            .child("users")
            .child(userId)
            .child("balance")
            .setValue(initialBalance)
            .await()


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

    private suspend fun emitLoginError(e: FirebaseException) {
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

    override fun logOutFromAccount() {
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

    private suspend fun startLoading() {
        _authState.emit(AuthResult.Loading)
    }

    private suspend fun emitError(error: String) {
        _authState.emit(AuthResult.Error(error))
    }

}