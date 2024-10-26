package com.akendardi.cryptowallet.data.repositories.auth

import android.util.Log
import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.states.auth.AuthResult
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {


    private val _authState = MutableStateFlow<AuthResult>(AuthResult.Initial)
    override val authState: StateFlow<AuthResult> = _authState.asStateFlow()
//
//    val scope = CoroutineScope(Dispatchers.IO)
//
//    init {
//        scope.launch {
//            _authState.collect {
//                Log.d("AuthRepositoryImpl", it.toString())
//            }
//        }
//    }

    override suspend fun createAccount(
        name: String,
        email: String,
        password: String
    ) {
        try {
            _authState.emit(AuthResult.Loading)
            auth.createUserWithEmailAndPassword(email, password).await()
            auth.currentUser?.updateProfile(
                UserProfileChangeRequest.Builder().setDisplayName(name).build()
            )?.await()
            _authState.emit(AuthResult.SuccessCreatedAccount)
        } catch (e: FirebaseAuthUserCollisionException) {
            _authState.emit(AuthResult.Error("Пользователь с таким аккаунтом уже существует"))
        }
    }

    override suspend fun logInAccount(email: String, password: String) {
        try {
            _authState.emit(AuthResult.Loading)
            auth.signInWithEmailAndPassword(email, password).await()
            _authState.emit(AuthResult.SuccessLogin)
        } catch (e: FirebaseException) {
            when (e) {
                is FirebaseAuthInvalidCredentialsException -> {
                    if (e.errorCode == "ERROR_WRONG_PASSWORD") {
                        _authState.emit(AuthResult.Error("Неверный пароль. Повторите попытку."))
                    } else {
                        _authState.emit(AuthResult.Error("Вы ввели неверные данные. Повторите попытку."))
                    }
                }

                is FirebaseAuthInvalidUserException -> {
                    _authState.emit(AuthResult.Error("Пользователь с такими учетными данными не найден."))
                }

                is FirebaseTooManyRequestsException -> {
                    _authState.emit(AuthResult.Error("Слишком много неудачных попыток входа. Попробуйте позже или сбросьте пароль."))
                }

                else -> {
                    _authState.emit(AuthResult.Error("Неизвестная ошибка: ${e.message}"))
                }
            }
        }
    }


    override suspend fun resetPasswordWithEmail(email: String) {
        try {
            auth.sendPasswordResetEmail(email).await()
            _authState.emit(AuthResult.SuccessSentLink)

        } catch (e: Exception) {
            _authState.emit(AuthResult.Error("Неизвестная ошибка: ${e.message}"))
        }
    }

    override suspend fun logOutFromAccount() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            _authState.emit(AuthResult.Error("Неизвестная ошибка: ${e.message}"))
        }
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

}