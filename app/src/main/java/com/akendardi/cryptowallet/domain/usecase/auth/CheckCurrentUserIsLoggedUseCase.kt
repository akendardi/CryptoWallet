package com.akendardi.cryptowallet.domain.usecase.auth

import com.akendardi.cryptowallet.domain.repository.AuthRepository
import javax.inject.Inject

class CheckCurrentUserIsLoggedUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() =
        authRepository.checkCurrentUserIsLogged()
}