package com.akendardi.cryptowallet.domain.usecase.auth

import com.akendardi.cryptowallet.domain.repository.AuthRepository
import javax.inject.Inject

class LogOutFromAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() =authRepository
        .logOutFromAccount()
}