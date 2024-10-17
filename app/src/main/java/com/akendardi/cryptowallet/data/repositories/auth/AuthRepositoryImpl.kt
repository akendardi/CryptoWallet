package com.akendardi.cryptowallet.data.repositories.auth

import com.akendardi.cryptowallet.data.repositories.internet_usecase.CheckInternetConnectionUseCase
import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.states.auth.AuthResult
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val checkInternetConnectionUseCase: CheckInternetConnectionUseCase
) : AuthRepository {
    override suspend fun createAccount(name: String, email: String, password: String): AuthResult {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            auth.currentUser?.updateProfile(
                UserProfileChangeRequest.Builder().setDisplayName(name).build()
            )?.await()
            AuthResult.Success
        } catch (e: FirebaseAuthUserCollisionException) {
            AuthResult.Error("Пользователь с таким аккаунтом уже существует")
        }
    }

    override suspend fun logInAccount(email: String, password: String): AuthResult {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            AuthResult.Success
        } catch (e: FirebaseException) {
            when (e) {
                is FirebaseAuthInvalidCredentialsException -> {
                    if (e.errorCode == "ERROR_WRONG_PASSWORD") {
                        AuthResult.Error("Неверный пароль. Повторите попытку.")
                    } else {
                        AuthResult.Error("Вы ввели неверные данные. Повторите попытку.")
                    }
                }

                is FirebaseAuthInvalidUserException -> {
                    AuthResult.Error("Пользователь с такими учетными данными не найден.")
                }

                is FirebaseTooManyRequestsException -> {
                    AuthResult.Error("Слишком много неудачных попыток входа. Попробуйте позже или сбросьте пароль.")
                }

                else -> {
                    AuthResult.Error("Неизвестная ошибка: ${e.message}")
                }
            }
        }
    }


    override suspend fun resetPasswordWithEmail(email: String): AuthResult {
        return try {
            auth.sendPasswordResetEmail(email).await()
            AuthResult.SuccessSentLink

        } catch (e: Exception) {
            AuthResult.Error("Произошла ошибка: ${e.message}")
        }
    }

    override suspend fun logOutFromAccount() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            AuthResult.Error(e.message.toString())
        }
    }

    override fun checkCurrentUserIsLogged(): Boolean {
        return (auth.currentUser?.reload() != null)
    }

    override fun checkInternetConnection(): Boolean = checkInternetConnectionUseCase()
}