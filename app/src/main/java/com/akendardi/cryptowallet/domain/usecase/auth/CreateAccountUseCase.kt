package com.akendardi.cryptowallet.domain.usecase.auth

import com.akendardi.cryptowallet.domain.repository.AuthRepository
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(name: String, password: String, email: String) =
        authRepository.createAccount(
            name, password, email
        )
}